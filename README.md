# Sloth

![Sloth](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdSR5mlbRGEPwFDk38Tp19tGgXo1vrEB6L0JosG0HXnNl8cScPOQ)

Runtime Permission library

## How to use

```java
Sloth
  .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
  .code(2333)
  .callback(
        (permissions, requestAction) ->
            Toast.makeText(this, "show rationale", Toast.LENGTH_SHORT).show() ,
        (code, grantedPermissions) ->
            Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show(),
        (code, DeniedPermissions, goSettingAction) ->
            Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show())
  .commit(act);
```
or
```kotlin
Sloth
	.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
	.code(2333)
	.callback(
      	{permissions, requestAction ->
            Toast.makeText(this, "show rationale", Toast.LENGTH_SHORT).show() },
        {code, grantedPermissions ->
            Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show()},
        {code, DeniedPermissions, goSettingAction ->
            Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()})
  	.commit(act);
```

As you can see , we don't need to override the `onRequestPermissionsResult` method in fragment or act , so we can complete our request process in a more fluent way and improve the code's readability .

And the entire action won't trigger unless you call `commit` when the configuration finished



## How to integrate

```groovy
compile 'com.xiansenliu.sloth:sloth:1.0.1'
```
or
```groovy
implementation 'com.xiansenliu.sloth:sloth:1.0.1'
```
