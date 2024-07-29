package com.wypaperplane.syscore.mapper;

import tk.mybatis.mapper.common.*;

@tk.mybatis.mapper.annotation.RegisterMapper
public interface CustomMapper<T> extends
        BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        SaveMapper<T>,
        IdsMapper<T>,
        Marker {}
