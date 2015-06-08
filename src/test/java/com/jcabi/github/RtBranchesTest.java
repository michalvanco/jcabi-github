/**
 * Copyright (c) 2013-2015, jcabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi.github;

import com.jcabi.github.mock.MkGithub;
import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.FakeRequest;
import com.jcabi.http.request.JdkRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link RtBranches}.
 *
 * @author Chris Rebert (github@rebertia.com)
 * @version $Id$
 */
public final class RtBranchesTest {
    /**
     * Name parameter.
     */
    private static final String NAME_PARAM = "name";
    /**
     * Name of test user to own test repository.
     */
    private static final String REPO_USER = "mary";
    /**
     * Name of test repository.
     */
    private static final String REPO_NAME = "epicness";

    /**
     * RtBranches can iterate over all branches.
     * @throws Exception if there is any error
     */
    @Test
    public void iteratesOverBranches() throws Exception {
        final String firstname = "first";
        final String firstsha = "a971b1aca044105897297b87b0b0983a54dd5817";
        final String secondname = "second";
        final String secondsha = "5d8dc2acf9c95d0d4e8881eebe04c2f0cbb249ff";
        final MkAnswer answer = new MkAnswer.Simple(
            HttpURLConnection.HTTP_OK,
            Json.createArrayBuilder()
                .add(branch(firstname, firstsha))
                .add(branch(secondname, secondsha))
                .build().toString()
        );
        final MkContainer container = new MkGrizzlyContainer()
            .next(answer)
            .next(answer)
            .start();
        final RtBranches branches = new RtBranches(
            new JdkRequest(container.home()),
            repository()
        );
        MatcherAssert.assertThat(
            branches.iterate(),
            Matchers.<Branch>iterableWithSize(2)
        );
        final Iterator<Branch> iter = branches.iterate().iterator();
        final Branch first = iter.next();
        MatcherAssert.assertThat(first.name(), Matchers.equalTo(firstname));
        MatcherAssert.assertThat(
            first.commit().sha(),
            Matchers.equalTo(firstsha)
        );
        final Branch second = iter.next();
        MatcherAssert.assertThat(second.name(), Matchers.equalTo(secondname));
        MatcherAssert.assertThat(
            second.commit().sha(),
            Matchers.equalTo(secondsha)
        );
        container.stop();
    }

    /**
     * RtBranches can fetch its repository.
     * @throws IOException If there is any I/O problem
     */
    @Test
    public void fetchesRepo() throws IOException {
        final Repo repo = RtBranchesTest.repository();
        final RtBranches branch = new RtBranches(new FakeRequest(), repo);
        final Coordinates coords = branch.repo().coordinates();
        MatcherAssert.assertThat(coords.user(), Matchers.equalTo(REPO_USER));
        MatcherAssert.assertThat(coords.repo(), Matchers.equalTo(REPO_NAME));
    }

    /**
     * Create and return JsonObject to test.
     * @param name Name of the branch
     * @param sha Commit SHA of the branch
     * @return JsonObject
     * @throws Exception If some problem inside
     */
    private static JsonObject branch(final String name, final String sha)
        throws Exception {
        return Json.createObjectBuilder()
            .add(NAME_PARAM, name)
            .add(
                "commit",
                Json.createObjectBuilder()
                    .add("sha", sha)
                    // @checkstyle LineLengthCheck (1 line)
                    .add("url", String.format("https://api.jcabi-github.invalid/repos/user/repo/commits/%s", sha))
            )
            .build();
    }

    /**
     * Mock repo for RtBranch creation.
     * @return The mock repo.
     * @throws IOException If there is any I/O problem
     */
    private static Repo repository() throws IOException {
        return new MkGithub(REPO_USER).repos().create(
            new Repos.RepoCreate(REPO_NAME, false)
        );
    }
}