/**
 * Copyright (c) 2012-2013, JCabi.com
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

import java.net.URL;
import javax.json.Json;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test case for {@link Content}.
 * @author Paul Polischuk (ppol@ua.fm)
 * @version $Id$
 * @checkstyle MultipleStringLiterals (500 lines)
 */
public class ContentTest {
    /**
     * Content.Smart can fetch type property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesType() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                .add("type", "this is some type")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).type(),
            Matchers.is("this is some type")
        );
    }

    /**
     * Content.Smart can fetch size property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesSize() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                // @checkstyle MagicNumber (1 line)
                .add("size", 5555)
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).size(),
            // @checkstyle MagicNumber (1 line)
            Matchers.is(5555)
        );
    }

    /**
     * Content.Smart can fetch name property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesName() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                .add("name", "this is some name")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).name(),
            Matchers.is("this is some name")
        );
    }

    /**
     * Content.Smart can fetch path property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesPath() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                .add("path", "this is some path")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).path(),
            Matchers.is("this is some path")
        );
    }

    /**
     * Content.Smart can fetch sha property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesSha() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                .add("sha", "this is some sha")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).sha(),
            Matchers.is("this is some sha")
        );
    }

    /**
     * Content.Smart can fetch url property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesUrl() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                // @checkstyle LineLength (1 line)
                .add("url", "https://api.github.com/repos/pengwynn/octokit/contents/README.md")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).url(),
            // @checkstyle LineLength (1 line)
            Matchers.is(new URL("https://api.github.com/repos/pengwynn/octokit/contents/README.md"))
        );
    }

    /**
     * Content.Smart can fetch git_url property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesGitUrl() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                // @checkstyle LineLength (1 line)
                .add("git_url", "https://api.github.com/repos/pengwynn/octokit/git/blobs/3d21ec53a331a6f037a91c368710b99387d012c1")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).gitUrl(),
            // @checkstyle LineLength (1 line)
            Matchers.is(new URL("https://api.github.com/repos/pengwynn/octokit/git/blobs/3d21ec53a331a6f037a91c368710b99387d012c1"))
        );
    }

    /**
     * Content.Smart can fetch html_url property from Content.
     * @throws Exception If some problem inside
     */
    @Test
    public final void fetchesHtmlUrl() throws Exception {
        final Content content = Mockito.mock(Content.class);
        Mockito.doReturn(
            Json.createObjectBuilder()
                // @checkstyle LineLength (1 line)
                .add("html_url", "https://github.com/pengwynn/octokit/blob/master/README.md")
                .build()
        ).when(content).json();
        MatcherAssert.assertThat(
            new Content.Smart(content).htmlUrl(),
            // @checkstyle LineLength (1 line)
            Matchers.is(new URL("https://github.com/pengwynn/octokit/blob/master/README.md"))
        );
    }
}
