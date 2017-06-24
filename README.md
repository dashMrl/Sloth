# Sloth

![Sloth](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdSR5mlbRGEPwFDk38Tp19tGgXo1vrEB6L0JosG0HXnNl8cScPOQ)

Runtime Permission library , it supply you a request chain like way to get permissions at runtime .

## How to use

```java
Sloth
  .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
  .requestCode(2333)
  .onRational((permissions, requestAction) -> 
              Toast.makeText(this, "show rationale", Toast.LENGTH_SHORT).show() )
  .afterGranted((requestCode, grantedPermissions) -> 
                Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show())
  .afterDenied((requestCode, DeniedPermissions, goSettingAction) -> 
               Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show())
  .commit(activity);
```

As you can see , we don't need to override the `onRequestPermissionsResult` method in fragment or activity , so we can complete our request process in a more fluent way and improve the code's readability .

There are some keys you should know :

- the entire action won't trigger unless you call `commit` when finished the configuration
- when `afterDenied` is called , you lost your ability to continue configuring . Make sure call `afterDenied` at the end of configuration
- the variable `requestAction` will request permissions directly if its `invoke` method is called
- the variable `goSettingAction` will navigate you to the setting page of your app



## How to integrate

```groovy
compile 'com.xiansenliu.sloth:sloth:latestVersion'
or
implementation 'com.xiansenliu.sloth:sloth:lastestVersionn'
```
