!function(t,n){"object"==typeof exports&&"undefined"!=typeof module?module.exports=n():"function"==typeof define&&define.amd?define(n):((t=t||self)["vdp_translation_sr-CYRL"]=t["vdp_translation_sr-CYRL"]||{},t["vdp_translation_sr-CYRL"].js=n())}(this,function(){"use strict";function t(t,n){for(var e=0;e<n.length;e++){var r=n[e];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(t,r.key,r)}}return new(function(){function n(t,e,r,i){!function(t,n){if(!(t instanceof n))throw new TypeError("Cannot call a class as a function")}(this,n),this.language=t,this.months=e,this.monthsAbbr=r,this.days=i,this.rtl=!1,this.ymd=!1,this.yearSuffix=""}var e,r,i;return e=n,(r=[{key:"language",get:function(){return this._language},set:function(t){if("string"!=typeof t)throw new TypeError("Language must be a string");this._language=t}},{key:"months",get:function(){return this._months},set:function(t){if(12!==t.length)throw new RangeError("There must be 12 months for ".concat(this.language," language"));this._months=t}},{key:"monthsAbbr",get:function(){return this._monthsAbbr},set:function(t){if(12!==t.length)throw new RangeError("There must be 12 abbreviated months for ".concat(this.language," language"));this._monthsAbbr=t}},{key:"days",get:function(){return this._days},set:function(t){if(7!==t.length)throw new RangeError("There must be 7 days for ".concat(this.language," language"));this._days=t}}])&&t(e.prototype,r),i&&t(e,i),n}())("Serbian in Cyrillic script",["Јануар","Фебруар","Март","Април","Мај","Јун","Јул","Август","Септембар","Октобар","Новембар","Децембар"],["Јан","Феб","Мар","Апр","Мај","Јун","Јул","Авг","Сеп","Окт","Нов","Дец"],["Нед","Пон","Уто","Сре","Чет","Пет","Суб"])});
