/*!
 * Tistory Javascript Framework, v0.1
 * Copyright (c) 2010 UCC FT Team, Front-end Technology Center, Daum Communications.
 * Licensed under Daum Common License : http://dna.daumcorp.com/forge/docs/daum-license-1.0.txt
 *
 * Includes jigu.js
 * Copyright (c) 2009 Front-end Technology Center, Daum Communications.
 * Project site: http://play.daumcorp.com/display/ftst/Jigu+Javascript+Framework
 * Licensed under Daum Common License : http://dna.daumcorp.com/forge/docs/daum-license-1.0.txt
 *
 * Date:
 */
(function(){
	if (!window.T) {
		/**
		 * @name T
		 * @namespace Tistory Javascript Framework의 namespace
		 */
		var T = window.T = {};

		T.domReady = function(fn){
			var ie = /*@cc_on!@*/ false;
			if (window.addEventListener) {
				//alert('DOMContentLoaded');
				document.addEventListener("DOMContentLoaded", fn, false);
			} else if (ie) { // IE
				document.attachEvent('onreadystatechange', function() {
					if (document.readyState == 'complete') {
						fn();
					}
				});
			}
		};

		T.cloneObject = function(obj) {
			if (obj && typeof(obj) == 'object') {
				var newObj = {};
				for (var p in obj) {
					newObj[p] = obj[p];
				}
				return newObj;
			}
		};

		T.admin = {};
		T.blog = {};
		T.ui = {};
		T.util = {};
		T.util.Cookie = {
			set: function(name, value, time, domain) {
				var maxAge = 0, expires = '', _domain = '';
				if (time) {
//					var date = new Date();
//					date.setTime(date.getTime() + titme);
//					expires = date.toGMTString();
					maxAge = '; max-age=' + time;
				}
				if (domain) {
					_domain = 'domain='+ domain;
				}
				document.cookie = name + "=" + value + maxAge + "; path=/;" + _domain;
			},

			get: function(name) {
				var cookies = document.cookie.split(";");
				for(var i = cookies.length; i--;) {
					if(cookies[i].indexOf("=") == -1) {
						if(name == cookies[i])
							return true;
					}
					else {
						var crumb = cookies[i].split("=");
						if(name == crumb[0].trim())
							return crumb[1].trim();
					}
				}
			},

			remove: function(name) {
				var date = new Date();
				date.setTime(date.getTime() - 1000);
				document.cookie = name + '=1; expires=' + date.toGMTString() + '; path=/; max-age=0';
			}
		};
	};
})();