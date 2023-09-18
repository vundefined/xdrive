package com.wypaperplane.syscore.support;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class YmlPropertySourceFactory {

    public YmlPropertySourceFactory() {}

    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
        String filename = resource.getResource().getFilename();
        String resourceName = Optional.ofNullable(name).orElse(filename);
        if (filename.endsWith("yml") || filename.endsWith("yaml")) {
            // List<PropertySource<?>> yamlSources = new YamlPropertySourceLoader().load(resourceName, resource.getResource());
            return null; // yamlSources.get(0);
        } else {
            return new DefaultPropertySourceFactory().createPropertySource(name, resource);
        }
    }
}
