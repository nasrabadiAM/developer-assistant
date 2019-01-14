/*
 *     This is the source code of developer-assistant project.
 *     Copyright (C)   Ali Nasrabadi<nasrabadiam@gmail.com>  2018-2019
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nasrabadiam.developerassistant.uitestutlis;

import androidx.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.*;

public final class LongListMatchers {

    private LongListMatchers() {
    }


    /**
     * Creates a matcher against the text stored in R.id.item_content. This text is roughly
     * "item: $row_number".
     */
    public static Matcher<Object> withItemContent(String expectedText) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(expectedText);
        return withItemContent(equalTo(expectedText));
    }

    /**
     * Creates a matcher against the text stored in R.id.item_content. This text is roughly
     * "item: $row_number".
     */
    @SuppressWarnings("rawtypes")
    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(itemTextMatcher);
        return new BoundedMatcher<Object, Map>(Map.class) {
            @Override
            public boolean matchesSafely(Map map) {
                return hasEntry(equalTo("STR"), itemTextMatcher).matches(map);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }

    /**
     * Creates a matcher against the text stored in R.id.item_size. This text is the size of the text
     * printed in R.id.item_content.
     */
    public static Matcher<Object> withItemSize(int itemSize) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkArgument(itemSize > -1);
        return withItemSize(equalTo(itemSize));
    }

    /**
     * Creates a matcher against the text stored in R.id.item_size. This text is the size of the text
     * printed in R.id.item_content.
     */
    @SuppressWarnings("rawtypes")
    public static Matcher<Object> withItemSize(final Matcher<Integer> itemSizeMatcher) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(itemSizeMatcher);
        return new BoundedMatcher<Object, Map>(Map.class) {
            @Override
            public boolean matchesSafely(Map map) {
                return hasEntry(equalTo("LEN"), itemSizeMatcher).matches(map);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item size: ");
                itemSizeMatcher.describeTo(description);
            }
        };
    }
}
