# Sloth

![Sloth](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdSR5mlbRGEPwFDk38Tp19tGgXo1vrEB6L0JosG0HXnNl8cScPOQ)

a simple Runtime Permission library helps you requesting permissions in a **fluent/chain-like** way.


## How to use (in Kotlin)
```kotlin
Sloth.with(this)
        .code(2333)
        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
        .callback(
                RationaleCallback { permissions, requestAction ->
                    Toast.makeText(this, "show rationale", Toast.LENGTH_SHORT).show()
                    AlertDialog.Builder(this@MainActivity)
                            .setTitle("The reason for permissions")
                            .setMessage("need some permissions to ensure that the app will run properly")
                            .setPositiveButton("OK") { dialogInterface, i -> requestAction.invoke() }
                            .show()
                },
                OnGrantedCallback { requestCode, grantedPermissions ->
                    Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show()
                },
                OnDeniedCallback { requestCode, deniedPermissions, goSettingAction ->
                    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()
                    AlertDialog.Builder(this@MainActivity)
                            .setTitle("Request was denied")
                            .setMessage("go to the app detail page and grant the permissions manually")
                            .setPositiveButton("Go") { dialogInterface, i -> goSettingAction.invoke() }
                            .show()
                })
        .commit()
```


## How to integrate
```groovy
implementation "com.github.xiansenLiu:Sloth:v1.0.4"
```

## License 

```
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
   Copyright 2017 xiansenLiu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
