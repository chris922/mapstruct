/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.lists;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListIndexMapper {

    ListIndexMapper INSTANCE = Mappers.getMapper( ListIndexMapper.class );

    @Mapping(target = "entry0", source = "stringList[0]")
    @Mapping(target = "entry1", source = "stringList[1]")
    Target<String, String> mapStringString(Source source);

}
