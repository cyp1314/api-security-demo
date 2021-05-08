### 实现接口安全性处理

前端代码

![](https://i.loli.net/2021/05/08/OTESnF3drB6XUKY.png)

![](https://i.loli.net/2021/05/08/dtiDmWnsO1X4RCU.png)

```javascript
var data = { id: 1, username: "lisi", password: "123456" };

// var newData = {};
// Object.keys(data)
//   .sort()
//   .map((key) => {
//     newData[key] = data[key];
//   });

// console.log(data);
// console.log(newData);

var s = "";
var arr = [];
for (var key in data) {
  arr.push(key);
}
arr = arr.sort();
var newData = {};
for (var i in arr) {
  var itemKey = arr[i];
  //   newData[itemKey] = data[itemKey];
  s += itemKey + "=" + data[itemKey] + "&";
}
s += "key=192006250b4c09247ec02edce69f6a2d";

console.log("--->", s);
var CryptoJS = require("crypto-js");
var hash = CryptoJS.MD5(s);
console.log(hash.toString().toUpperCase());

```


后端主要代码
1. 参见拦截器


### 项目难点
1. 拦截器中获取了 body 体的参数后， controller 层里面获取不到body 体的参数。这个解决办法 参见 
https://www.codeleading.com/article/80061204466/