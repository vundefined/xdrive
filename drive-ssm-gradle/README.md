- [接口文档 knife4j](https://github.com/xiaoymin/knife4j)
- [rabbit](http://localhost:15672/#/) `wyrabbit 12345678`

# 代码片段

```
List<String> cacheNames = new ArrayList<>();
cacheNames.add("cachea");
cacheManager.setCacheNames(cacheNames);

@Autowired
private CacheManager cacheManager;

wxMiniService.getDriveLicenseClassTree();
Cache cache = cacheManager.getCache("licenseClass");
System.out.println(cache);
if (!ObjectUtils.isEmpty(cache)) {
    Cache.ValueWrapper wrapper = cache.get("0");
    System.out.println(wrapper);
}
```


