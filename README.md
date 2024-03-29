# Custom Bottom Sheet Date Picker (For android java)
The Custom Bottom Sheet Date Picker is a dependency library designed for Android apps. It is inspired by the iOS date picker style and provides a user-friendly interface for selecting dates. With this library, developers can easily integrate a sleek and modern date picker into their app, without the need for extensive coding. The bottom sheet design allows users to easily select their preferred date, while the library's ease of use makes it a convenient solution for developers.

<br>

<a href="https://www.buymeacoffee.com/smkamal" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-blue.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>

<br>

![image](https://github.com/shahi5472/custom-date-picker/blob/master/app/src/main/res/drawable/Screenshot_2020-06-13-03-46-55-687_com.s.m.shahi.customdatepicker_half.jpg)

# Gradle Setup

In your app's `build.gradle` add

```

dependencies {
    implementation 'com.github.shahi5472:custom-date-picker:1.1.0'
}


```

Add the following to your project level build.gradle:

```

allprojects {
  repositories {
    maven {
      url "https://jitpack.io" 
      }
   }
}

```

# Usage

```

AppCompatActivity implements DatePickerListener

DatePicker datePicker = new DatePicker();

datePicker.setDatePickerConfirmButton("ok", ContextCompat.getColor(this, R.color.confirm_btn_color),
                false, ContextCompat.getDrawable(this, R.drawable.confirm_button_bg));

//Individual
datePicker.setConfirmButtonText("ok");
datePicker.setConfirmButtonTextColor(ContextCompat.getColor(this, R.color.confirm_btn_color));
datePicker.setConfirmButtonTextAllCaps(true);
datePicker.setConfirmButtonBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_bg));

datePicker.setDatePickerCancelButton("dismiss", ContextCompat.getColor(this, R.color.cancel_btn_color),
                false, ContextCompat.getDrawable(this, R.drawable.cancel_button_bg));

//Individual
datePicker.setCancelButtonText("dismiss");
datePicker.setCancelButtonTextColor(ContextCompat.getColor(this, R.color.cancel_btn_color));
datePicker.setCancelButtonTextAllCaps(true);
datePicker.setCancelButtonBackground(ContextCompat.getDrawable(this, R.drawable.cancel_button_bg));

findViewById(R.id.clickImage).setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         datePicker.show(getSupportFragmentManager(), datePicker.getTag());
     }
});


@Override
public void onPickupDate(String value) {
    Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
}

@Override
public void onDay(String day) {
    Toast.makeText(this, ""+day, Toast.LENGTH_SHORT).show();
}

@Override
public void onMonth(String month) {
    Toast.makeText(this, ""+month, Toast.LENGTH_SHORT).show();
}

@Override
public void onYear(String year) {
    Toast.makeText(this, ""+year, Toast.LENGTH_SHORT).show();
}
    
    
```

# Contributing
Please open an issue first before making a pull request. Pull requests should be accompanied by tests if possible.

# License
Copyright 2020 AOSP, S.M. SHAHi

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

`http://www.apache.org/licenses/LICENSE-2.0`

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

![image](https://github.com/shahi5472/custom-date-picker/blob/master/app/src/main/res/drawable/Screenshot_2020-06-13-03-46-23-638_com.s.m.shahi.customdatepicker.jpg)

![image](https://github.com/shahi5472/custom-date-picker/blob/master/app/src/main/res/drawable/Screenshot_2020-06-13-03-46-30-260_com.s.m.shahi.customdatepicker.jpg)

![image](https://github.com/shahi5472/custom-date-picker/blob/master/app/src/main/res/drawable/Screenshot_2020-06-13-03-46-55-687_com.s.m.shahi.customdatepicker.jpg)

![image](https://github.com/shahi5472/custom-date-picker/blob/master/app/src/main/res/drawable/Screenshot_2020-06-13-03-47-45-999_com.s.m.shahi.customdatepicker.jpg)
