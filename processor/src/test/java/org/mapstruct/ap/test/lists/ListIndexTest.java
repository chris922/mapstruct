/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.lists;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.testutil.IssueKey;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@WithClasses({
    ListIndexMapper.class,
    Source.class,
    Target.class
})
@IssueKey("1321")
@RunWith(AnnotationProcessorTestRunner.class)
public class ListIndexTest {
    @Test
    public void mapsCorrectly() {
        Source source = new Source();
        source.setStringList( Lists.newArrayList( "first", "second" ) );

        Target<String, String> target = ListIndexMapper.INSTANCE.mapStringString( source );

        assertThat( target ).isNotNull();
        assertThat( target.getEntry0() ).isEqualTo( "first" );
        assertThat( target.getEntry1() ).isEqualTo( "second" );
    }
}
