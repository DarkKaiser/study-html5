//<![CDATA[
var sUserAgent = navigator.userAgent;
var fAppVersion = parseFloat(navigator.appVersion);
var daumMusicPlayerList = [];

function compareVersions(sVersion1, sVersion2) {

	var aVersion1 = sVersion1.split(".");
	var aVersion2 = sVersion2.split(".");

	if (aVersion1.length > aVersion2.length) {
		for (var i=0; i < aVersion1.length - aVersion2.length; i++) {
			aVersion2.push("0");
		}
	} else if (aVersion1.length < aVersion2.length) {
		for (var i=0; i < aVersion2.length - aVersion1.length; i++) {
			aVersion1.push("0");
		}
	}
	for (var i=0; i < aVersion1.length; i++) {

		if (aVersion1[i] < aVersion2[i]) {
			return -1;
		} else if (aVersion1[i] > aVersion2[i]) {
			return 1;
		}
	}
	return 0;

}

var isOpera = sUserAgent.indexOf("Opera") > -1;
var isMinOpera4 = isMinOpera5 = isMinOpera6 = isMinOpera7 = isMinOpera7_5 = false;

if (isOpera) {
	var fOperaVersion;
	if(navigator.appName == "Opera") {
		fOperaVersion = fAppVersion;
	} else {
		var reOperaVersion = new RegExp("Opera (\\d+\\.\\d+)");
		reOperaVersion.test(sUserAgent);
		fOperaVersion = parseFloat(RegExp["$1"]);
	}

	isMinOpera4 = fOperaVersion >= 4;
	isMinOpera5 = fOperaVersion >= 5;
	isMinOpera6 = fOperaVersion >= 6;
	isMinOpera7 = fOperaVersion >= 7;
	isMinOpera7_5 = fOperaVersion >= 7.5;
}

var isKHTML = sUserAgent.indexOf("KHTML") > -1
			  || sUserAgent.indexOf("Konqueror") > -1
			  || sUserAgent.indexOf("AppleWebKit") > -1;

var isMinSafari1 = isMinSafari1_2 = false;
var isMinKonq2_2 = isMinKonq3 = isMinKonq3_1 = isMinKonq3_2 = false;
var isSafari = false;
if (isKHTML) {
	isSafari = sUserAgent.indexOf("AppleWebKit") > -1;
	isKonq = sUserAgent.indexOf("Konqueror") > -1;

	if (isSafari) {
		var reAppleWebKit = new RegExp("AppleWebKit\\/(\\d+(?:\\.\\d*)?)");
		reAppleWebKit.test(sUserAgent);
		var fAppleWebKitVersion = parseFloat(RegExp["$1"]);

		isMinSafari1 = fAppleWebKitVersion >= 85;
		isMinSafari1_2 = fAppleWebKitVersion >= 124;
	} else if (isKonq) {

		var reKonq = new RegExp("Konqueror\\/(\\d+(?:\\.\\d+(?:\\.\\d)?)?)");
		reKonq.test(sUserAgent);
		isMinKonq2_2 = compareVersions(RegExp["$1"], "2.2") >= 0;
		isMinKonq3 = compareVersions(RegExp["$1"], "3.0") >= 0;
		isMinKonq3_1 = compareVersions(RegExp["$1"], "3.1") >= 0;
		isMinKonq3_2 = compareVersions(RegExp["$1"], "3.2") >= 0;
	}

}

var isIE = sUserAgent.indexOf("compatible") > -1
		   && sUserAgent.indexOf("MSIE") > -1
		   && !isOpera;

var isMinIE4 = isMinIE5 = isMinIE5_5 = isMinIE6 = false;

if (isIE) {
	var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
	reIE.test(sUserAgent);
	var fIEVersion = parseFloat(RegExp["$1"]);

	isMinIE4 = fIEVersion >= 4;
	isMinIE5 = fIEVersion >= 5;
	isMinIE5_5 = fIEVersion >= 5.5;
	isMinIE6 = fIEVersion >= 6.0;
}

var isMoz = sUserAgent.indexOf("Gecko") > -1
			&& !isKHTML;

var isMinMoz1 = sMinMoz1_4 = isMinMoz1_5 = false;

if (isMoz) {
	var reMoz = new RegExp("rv:(\\d+\\.\\d+(?:\\.\\d+)?)");
	reMoz.test(sUserAgent);
	isMinMoz1 = compareVersions(RegExp["$1"], "1.0") >= 0;
	isMinMoz1_4 = compareVersions(RegExp["$1"], "1.4") >= 0;
	isMinMoz1_5 = compareVersions(RegExp["$1"], "1.5") >= 0;
}

var isNS4 = !isIE && !isOpera && !isMoz && !isKHTML
			&& (sUserAgent.indexOf("Mozilla") == 0)
			&& (navigator.appName == "Netscape")
			&& (fAppVersion >= 4.0 && fAppVersion < 5.0);

var isMinNS4 = isMinNS4_5 = isMinNS4_7 = isMinNS4_8 = false;

if (isNS4) {
	isMinNS4 = true;
	isMinNS4_5 = fAppVersion >= 4.5;
	isMinNS4_7 = fAppVersion >= 4.7;
	isMinNS4_8 = fAppVersion >= 4.8;
}

var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC")
			|| (navigator.platform == "Macintosh");

var isUnix = (navigator.platform == "X11") && !isWin && !isMac;

var isWin95 = isWin98 = isWinNT4 = isWin2K = isWinME = isWinXP = false;
var isMac68K = isMacPPC = false;
var isSunOS = isMinSunOS4 = isMinSunOS5 = isMinSunOS5_5 = false;

if (isWin) {
	isWin95 = sUserAgent.indexOf("Win95") > -1
			  || sUserAgent.indexOf("Windows 95") > -1;
	isWin98 = sUserAgent.indexOf("Win98") > -1
			  || sUserAgent.indexOf("Windows 98") > -1;
	isWinME = sUserAgent.indexOf("Win 9x 4.90") > -1
			  || sUserAgent.indexOf("Windows ME") > -1;
	isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1
			  || sUserAgent.indexOf("Windows 2000") > -1;
	isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1
			  || sUserAgent.indexOf("Windows XP") > -1;
	isWinNT4 = sUserAgent.indexOf("WinNT") > -1
			  || sUserAgent.indexOf("Windows NT") > -1
			  || sUserAgent.indexOf("WinNT4.0") > -1
			  || sUserAgent.indexOf("Windows NT 4.0") > -1
			  && (!isWinME && !isWin2K && !isWinXP);
}

if (isMac) {
	isMac68K = sUserAgent.indexOf("Mac_68000") > -1
			   || sUserAgent.indexOf("68K") > -1;
	isMacPPC = sUserAgent.indexOf("Mac_PowerPC") > -1
			   || sUserAgent.indexOf("PPC") > -1;
}

if (isUnix) {
	isSunOS = sUserAgent.indexOf("SunOS") > -1;

	if (isSunOS) {
		var reSunOS = new RegExp("SunOS (\\d+\\.\\d+(?:\\.\\d+)?)");
		reSunOS.test(sUserAgent);
		isMinSunOS4 = compareVersions(RegExp["$1"], "4.0") >= 0;
		isMinSunOS5 = compareVersions(RegExp["$1"], "5.0") >= 0;
		isMinSunOS5_5 = compareVersions(RegExp["$1"], "5.5") >= 0;
	}
}

function analysis(msg,mode) {
	try {
		if(mode == undefined) {
			var temp ='<table border="1"  cellspacing="0">';
			for(var name in msg) {
				temp +='<tr>';
				temp +='<td>'+name+'</td><td>'
				temp += msg[name]
				temp +='</td>';
				temp +='</tr>';
			}
			temp +='</table>';
			return temp;
		} else if(mode ='a') {
			var temp ='';
			for(var name in msg) {
				temp +=name+'\t\t:'+msg[name]+'\n';
			}
			return temp
		}
	} catch (e) {
		//alert(e);
	}
}

function trace(msg,mode) {
	result = analysis(msg,mode);
	if(mode == undefined) {
		var traceWin = window.open('', "traceWin");
		traceWin.focus();
		traceWin.document.body.innerHTML = result;
	} else if(mode =='w') {
		alert(result);
	}
}

function openLinkInNewWindow(callee) {
	if (callee) {
		var url = callee.getAttribute("href");
		if (url) {
			window.open(url);
			return false;
		}
	}
	return true;
}


function toggleLayer(id) {
	try {
	var obj = document.getElementById(id);
	obj.style.display = (obj.style.display == "none") ? "block" : "none";
	} catch (e) {

	}
	return true;
}
function showLayer(id) {
	document.getElementById(id).style.display = "block";
	return true;
}
function tt_showLayer(id){
	document.getElementById(id).style.display = "block";
	return true;
}
function tt_hideLayer(id){
	document.getElementById(id).style.display = "none";
	return true;
}
function hideLayer(id) {
	document.getElementById(id).style.display = "none";
	return true;
}

function findFormObject(caller) {

	for (var obj = caller; obj; obj = obj.parentNode) {

		if (obj.nodeName == "FORM")
			return obj;
	}


	return null;
}

function trim(str) {
	var start = 0;
	var end = str.length;
	for (var i = 0; i < str.length; i ++) {
		if (str.charAt(i) != " ") {
			start = i;
			break;
		}
	}
	for (var i = str.length - 1; i >= -1; i --) {
		if (str.charAt(i) != " ") {
			end = i + 1;
			break;
		}
	}
	return str.substring(start, end);
}

function checkValue(oField, message) {
	try {
		if (oField.value.length == 0) {
			alert(message);
			oField.focus();
			return false;
		}
		return true;
	} catch(e) {

		return false;
	}
}

function trimAll(oForm) {
	try {
		for (var i = 0; i < oForm.elements.length; i ++) {
			var tagName = oForm.elements[i].tagName.toLowerCase();
			var type = oForm.elements[i].type;
			/*
			if((tagName == "input" && type == "text") || tagName == "textarea")
				oForm.elements[i].value = trim(oForm.elements[i].value);
			*/
		}
		return true;
	} catch (e) {
		alert(e.message);
	}
}

function openKeyword(url) {
	window.open(url, 'keyword', 'width=570,height=650,location=0,menubar=0,resizable=1,scrollbars=1,status=0,toolbar=0');
}

var oProgress = null;
function beginProgress() {
	endProgress();
	oProgress = document.createElement("span");
	oProgress.style.position = "absolute";
	oProgress.style.left = "0px";
	oProgress.style.top = "0px";
	oProgress.style.backgroundColor = "#FFFF99";
	oProgress.innerText = "???..";
	document.body.appendChild(oProgress);
}
function endProgress() {
	if (oProgress) {
		document.body.removeChild(oProgress);
		oProgress = null;
	}
}



if(isMoz) {
	XMLDocument.prototype.selectNodes= function(path) {
		var oEvaluator = new XPathEvaluator();
		var oResult = oEvaluator.evaluate(path, this, null, XPathResult.ORDERER_NODE_ITERATOR_TYPE, null);
		var result = new Array();
		var oElement = oResult.iterateNext();
		while(oElement) {
			result[oElement.nodeName]=oElement.firstChild.nodeValue;
			oElement = oResult.iterateNext();
		}
		return result;
	}
	XMLDocument.prototype.selectSingleNode = function(path) {
		var oEvaluator = new XPathEvaluator();
		var oResult = oEvaluator.evaluate(path, this, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);
		return oResult.singleNodeValue;
	}
	Node.prototype.__defineGetter__("xml",function() {
		var os = new XMLSerializer();
		return os.serializeToString(this,"text/xml");
	});
}

function createHttp() {
	try {
		return new XMLHttpRequest();
	}
	catch (e) {
		var objectNames = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
		for (var i = 0; i < objectNames.length; i ++) {
			try {
				return new ActiveXObject(objectNames[i]);
				break;
			}
			catch (e) {
			}
		}
		return null;
	}
}

/*
loadingIntervaler = function(loading) {
	try {
		if(loading) {
			document.body.style.cursor = "wait";
		} else {
			document.body.style.cursor = "default";
		}
		window.status = loading;
	} catch(e) {

	}
}

window.onload = function() {
	try {
		//setInterval("loadingIntervaler(loading)", 1);
	} catch(e) {
		alert(e.message);
	}
}
*/
var loading = false;

function getResponse(uri,content) {
	try {
		loading = true
		var body = document.body;
		var oHttp = createHttp();
		if(uri.indexOf('?') ==-1) aux = '?';
		else aux = '&';
		oHttp.open("POST", uri + aux+"time=" + (new Date()).getTime(), false);
		if(content == undefined) {
			content = '';
		} else {
			oHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		}
		oHttp.send(content);
		result = new Array();
		if(isSafari || isOpera) {
				resultNodes = oHttp.responseXML.firstChild.childNodes;
				for(var i=0; i<resultNodes.length; i++) {
					if(resultNodes.item(i).firstChild != null)
						result[resultNodes.item(i).nodeName]=resultNodes.item(i).firstChild.nodeValue;
				}
			loading = false
			delete oHttp;
			return result;
		} else if(isIE) {
			resultNodes = oHttp.responseXML.documentElement.childNodes;
			result = new Array();
			for(var i=0; i<resultNodes.length; i++) {
				result[resultNodes[i].nodeName] = resultNodes[i].text;
			}
			loading = false
			delete oHttp;
			return result;
		} else {
			loading = false
			var returnValue = oHttp.responseXML.selectNodes("/response/descendant::*");
			delete oHttp;
			return returnValue;
		}
		delete oHttp;
	} catch(e) {
		alert("exception");
		loading = false
		var escapeSpace = document.getElementsByName('body');
		var iframeElement = document.createElement('div');
		document.body.appendChild(iframeElement);
		iframeElement.innerHTML = '<iframe src="'+uri+'"style="display:none" onload="location.href=location.href"></iframe>';

		return false;
	}
}

function requestHttp(uri) {
	try{
		var oHttp = createHttp();
		oHttp.open("GET", uri + "&time=" + (new Date()).getTime(), false);
		oHttp.send("");
		if(isSafari || isOpera) {
			var returnValue = oHttp.responseXML.firstChild.firstChild.nextSibling.firstChild.nodeValue;
			delete oHttp;
			return returnValue;
		} else {
			var returnValue = oHttp.responseXML.selectSingleNode("/response/error").text;
			delete oHttp;
			return returnValue;
		}
	} catch (e) {
		window.status = e.messge;
	}
}

function requestHttpText(uri) {
	var oHttp = createHttp();
	oHttp.open("GET", uri + "&time=" + (new Date()).getTime(), false);
	oHttp.send("");
	var returnValue = oHttp.responseText;
	delete oHttp;
	return returnValue;
}

function requestHttpXml(uri) {
	var oHttp = createHttp();
	oHttp.open("GET", uri + "&time=" + (new Date()).getTime(), false);
	oHttp.send("");
	var returnValue = oHttp.responseXML;
	delete oHttp;
	return returnValue;
}

function requestPost(uri, content) {
	var oHttp = createHttp();
	oHttp.open("POST", uri + "&time=" + (new Date()).getTime(), false);
	oHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	oHttp.send(content+ "&time=" + (new Date()).getTime());
	var returnValue = oHttp.responseXML.selectSingleNode("/response/error").text;
	delete oHttp;
	return returnValue;
}

function requestPostText(uri, content) {
	var oHttp = createHttp();
	oHttp.open("POST", uri + "&time=" + (new Date()).getTime(), false);
	oHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	oHttp.send(content);
	var returnValue = oHttp.responseText;
	delete oHttp;
	return returnValue;
}

function setRequestBody(elementName, elementValue, boundary)
{
	var body = "";
	body += "--" + boundary + "\r\n";
	body += "Content-Disposition: form-data; name=\"" + elementName + "\"" + "\r\n\r\n";
	body += elementValue + "\r\n";
	return body;
}

function isNull(field,message) {
	if (field.value.length==0) {
		alert(message + '\t');
		field.focus();
		return true;
	}
	return false;
}

var tatterImagePopup = null;

function open_img(url) {
	try {
		var left = Math.floor((screen.availWidth - 250) / 2);
		var top = Math.floor((screen.availHeight - 100) / 2);
		try { tatterImagePopup.close(); } catch(e) { }
		tatterImagePopup = window.open("", "", "width=250, height=100, left=" + left + ", top=" + top + ", scrollbars=no, resizable=yes");
		tatterImagePopup.document.open("text/html", "replace");
		tatterImagePopup.document.write(
			'<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">\r\n' +
			'<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">\r\n' +
			'	<head>\r\n' +
			'		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>\r\n' +
			'		<title> :: View :: </title>\r\n' +
			'		<style type="text/css">\r\n' +
			'			html, body { width: 100%; height: 100%; margin: 0; padding: 0; cursor: pointer; text-align: center; line-height: 0; }\r\n' +
			'			div { width: 100%; height: 100%; overflow: auto; }\r\n' +
			'		</style>\r\n' +
			'		<script type="text/javascript">\r\n' +
			((navigator.userAgent.indexOf("Safari") > -1) ?
				'			function resizeImage() {\r\n' :
				'			window.onload = function() {\r\n') +
			'				var container = document.getElementById("Container");\r\n' +
			'				var image = document.getElementById("Image");\r\n' +
			'				var resizeWidth = 0, resizeHeight = 0, positionTop = 0, positionLeft = 0;\r\n' +
			'				var offsetTop = window.screenTop || window.screenY;\r\n' +
			'				var offsetLeft = window.screenLeft || window.screenX;\r\n' +
			'				if(navigator.userAgent.indexOf("Safari") > -1) {\r\n' +
			'					var width = Math.min(image.width + 50, screen.availWidth - 100);\r\n' +
			'					var height = Math.min(image.height + 50, screen.availHeight - 100);\r\n' +
			'					window.moveTo((screen.availWidth - width) / 2, (screen.availHeight - height) / 2);\r\n' +
			'					window.resizeTo(width, height);\r\n' +
			'					return;\r\n' +
			'				}\r\n' +
			'				if(container.scrollWidth > container.offsetWidth) {\r\n' +
			'					resizeWidth += container.scrollWidth - container.offsetWidth;\r\n' +
			'					if(container.offsetWidth + resizeWidth + 100 > screen.availWidth) {\r\n' +
			'						resizeWidth = screen.availWidth - container.offsetWidth - 100;\r\n' +
			'						positionLeft = -resizeWidth / 2;\r\n' +
			'						resizeHeight += 20;\r\n' +
			'					}\r\n' +
			'					else {\r\n' +
			'						positionLeft = -resizeWidth / 2;\r\n' +
			'					}\r\n' +
			'				}\r\n' +
			'				if(container.scrollHeight > container.offsetHeight) {\r\n' +
			'					resizeHeight += container.scrollHeight - container.offsetHeight;\r\n' +
			'					if(container.offsetHeight + resizeHeight + 100 > screen.availHeight - 50) {\r\n' +
			'						resizeHeight = screen.availHeight - container.offsetHeight - 100 - 40;\r\n' +
			'						positionTop = -resizeHeight / 2;\r\n' +
			'						resizeWidth += 20;\r\n' +
			'					}\r\n' +
			'					else {\r\n' +
			'						positionTop = -resizeHeight / 2;\r\n' +
			'					}\r\n' +
			'				}\r\n' +
			'				if(resizeWidth == 0 && resizeHeight == 0)\r\n' +
			'					image.style.marginTop = ((container.offsetHeight - image.height) / 2) + "px";\r\n' +
			'				window.moveBy(positionLeft, positionTop - 35);\r\n' +
			'				window.resizeBy(resizeWidth, resizeHeight);\r\n' +
			'			}\r\n' +
			'		</script>\r\n' +
			'	</head>\r\n' +
			'	<body>\r\n' +
			((navigator.userAgent.indexOf("Safari") > -1) ?
				'		<div id="Container"><img id="Image" src="' + url + '" alt="" onclick="window.close();" onload="resizeImage();"/></div>\r\n' :
				'		<div id="Container"><img id="Image" src="' + url + '" onclick="window.close();" alt=""/></div>\r\n') +
			'	</body>\r\n' +
			'</html>'
		);
		tatterImagePopup.document.close();
		if(tatterImagePopup.document.focus)
			tatterImagePopup.document.focus();
	}
	catch(e) {
		window.open(url, "_blank");
	}
}

function enlargeImagzing(params) {
	var win = window.open("", "img_popup", "width=" + (screen.width - (STD.isIE ? 10 : 0)) + ",height=" + (screen.availHeight - (STD.isIE ? 40 : 0)) + ",left=0,top=0,scrollbars=no,resizable=yes");
	win.status = params.caption;
	var form = document.createElement("form");
	form.action = params.action;
	form.target = "img_popup";
	form.method = "post";
	form.innerHTML = '\
		<input type="hidden" name="d" value="' + params.id + '"/>\
		<input type="hidden" name="f" value="' + params.frame + '"/>\
		<input type="hidden" name="t" value="' + params.transition + '"/>\
		<input type="hidden" name="n" value="' + params.navigation + '"/>\
		<input type="hidden" name="si" value="' + params.slideshowInterval + '"/>\
		<input type="hidden" name="p" value="' + params.page + '"/>\
		<input type="hidden" name="a" value="' + params.align + '"/>\
		<input type="hidden" name="i" value="' + decodeURIComponent(params.images) + '"/>\
		<input type="hidden" name="owner" value="' + params.owner + '"/>\
		<input type="hidden" name="caption" value="' + params.caption + '"/>\
		<input type="hidden" name="root" value="' + params.root + '"/>\
	';
	document.body.appendChild(form);
	form.submit();
	try { win.focus(); } catch(e) { }
}

function scroller(target, acceleration) {
	try {
		var target = document.getElementById(target);
		var dest = document.body.scrollTop;
		status = target.scrollTop+'  '+document.body.scrollTop+'  '+acceleration+' = '+((target.offsetTop - document.body.scrollTop)/acceleration)
		dest += (target.offsetTop - document.body.scrollTop)/acceleration
		if ( document.body.scrollTop == dest)
			clearInterval(scrollerId);
		window.scroll(0, dest);
	} catch(e) {
		clearInterval(scrollerId);
		alert(e.message);
	}
}

function eleganceScroll(target, acceleration) {
	if(acceleration == undefined)
		acceleration = 8;
	scrollerId = window.setInterval("scroller('"+target+"',"+acceleration+")",1000/30);
}

function showJukeboxList(id,height){
	target = document.getElementById('jukeBoxContainer'+id);
	divTarget = document.getElementById('jukeBox'+id+'Div');
	flashTarget = document.getElementById('jukeBox'+id+'Flash');
	target.style.height = flashTarget.style.height = divTarget.style.height = height+'px';
}

function setUserSetting(name, value) {
	var request = new HTTPRequest("POST", blogURL + "/admin/misc/setUserSetting.php");
	request.send("name=" + encodeURIComponent(name) + "&value=" + encodeURIComponent(value));
}

function getWindowCleintHeight() {
	return (window.innerHeight != null) ? window.innerHeight : document.documentElement.clientHeight;
}

function getWindowCleintWidth() {
	return (window.innerWidth != null) ? window.innerWidth : document.documentElement.clientWidth;
}

function getOffsetTop(obj)
{ return obj ? obj.offsetTop + getOffsetTop(obj.offsetParent) : 0; }

function getOffsetLeft(obj)
{ return obj ? obj.offsetLeft + getOffsetLeft(obj.offsetParent) : 0; }

function updateFeed()
{
	var http = createHttp();
	if(http) {
		http.open("GET", blogURL + "/feeder?" + (new Date()).getTime(), true);
		http.send("");
	}
}

function searchChildNodes(obj, tagName) {
	var nodes = new Array();
	if(obj.hasChildNodes()) {
		for(var i=0; i<obj.childNodes.length; i++) {
			var node = obj.childNodes[i];
			if(node.nodeType != 1)
				continue;
			if(node.tagName.toUpperCase() == tagName.toUpperCase())
				nodes[nodes.length] = node;
			var childNodes = searchChildNodes(node, tagName);
			for(var j=0; j<childNodes.length; j++) {
				nodes[nodes.length] = childNodes[j];
			}
		}
	}
	return nodes;
}

function getEmbedCode(movie,width,height,id,bg,FlashVars,menu, transparent, quality, bgcolor, allowScriptAccess, version){
	try {
		if(movie == undefined || width == undefined || height == undefined)
			return false;

		if ( FlashVars == undefined) {
			var _FlashVars_object = '';
			var _FlashVars_embed = '';
		} else {
			var _FlashVars_object = '<param name="FlashVars" value="'+FlashVars+'" />';
			var _FlashVars_embed = ' FlashVars="'+FlashVars+'" ';
		}

		if ( menu == undefined) {
			var _menu_object = '';
			var _menu_embed = '';
		} else {
			var _menu_object = '<param name="menu" value="'+menu+'" />';
			var _menu_embed = ' menu="'+menu+'" ';
		}

		if ( transparent == undefined) {
			var _transparent_object = '';
			var _transparent_embed = '';
		} else {
			var _transparent_object = '<param name="wmode" value="'+transparent+'" />';
			var _transparent_embed = ' wmode="'+transparent+'" ';
		}

		if ( quality == undefined) {
			var _quality_object = '';
			var _quality_embed = '';
		} else {
			var _quality_object = '<param name="quality" value="'+quality+'" />';
			var _quality_embed = ' quality="'+quality+'" ';
		}

		if ( bgcolor == undefined) {
			var _bgcolor_object = '';
			var _bgcolor_embed = '';
		} else {
			var _bgcolor_object = '<param name="bgcolor" value="'+bgcolor+'" />';
			var _bgcolor_embed = ' bgcolor="'+bgcolor+'" ';
		}

		var _allowScriptAccess_object = '<param name="allowScriptAccess" value="always" />';
		var _allowScriptAccess_embed = ' allowScriptAccess="always" ';

		if (id == undefined) {
			var _id = "";
		} else {
			var _id = 'id="'+id+'"';
		}


		if  (version == undefined) {
			version = '7,0,0,0';
		}

		if(STD.isIE) {
			return '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version='+version+'" width="'+width+'" height="'+height+'" '+_id+' align="middle"><param name="movie" value="'+movie+'" />'+_allowScriptAccess_object+_FlashVars_object+_menu_object+_quality_object+_bgcolor_object+_transparent_object+'</object>';
		} else {
			return '<embed '+_id+' type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" src="'+movie+'"'+' width="'+width+'"'+' height="'+height+'"'+_allowScriptAccess_embed+_FlashVars_embed+_menu_embed+_quality_embed+_bgcolor_embed+_transparent_embed+'/>'
		}

	} catch(e) {
		return false;
	}

}

function writeCode(str, id) {
	if(id == undefined) document.write(str);
	else document.getElementById(id).innerHTML = str;
}

function writeCode2(str, id) {
	if(id == undefined) document.write(str);
	else document.getElementById(id).innerHTML = str;
}
var StringBuffer = function()
{ this.buffer = new Array(); }

StringBuffer.prototype.append=function(str)
{ this.buffer[this.buffer.length] = str; }

StringBuffer.prototype.toString = function()
{ return this.buffer.join(""); }

if(!Array.prototype.push) {
	Array.prototype.push = function() {
		var startLength = this.length;
		for(var i=0; i<arguments.length; i++)
			this[startLength + i] = arguments[i];

		return this.length;
	}
}

if(!String.prototype.trim) {
	String.prototype.trim = function()
	{ return this.replace(new RegExp("(^\\s*)|(\\s*$)", "g"), ""); }
}

if(!String.prototype.replaceAll) {
	String.prototype.replaceAll = function(source, target) {
		source = source.replace(new RegExp("(\\W)", "g"), "\\$1");
		target = target.replace(new RegExp("\\$", "g"), "$$$$");
		return this.replace(new RegExp(source, "gm"), target);
	}
}

if(!String.prototype.count) {
	String.prototype.count = function(search) {
		if(typeof search == "string")
			var matches = this.match(new RegExp(search.replace(new RegExp("(\\W)", "g"), "\\$1"), "g"));
		else
			var matches = this.match(search);
		return matches ? matches.length : 0;
	}
}

if(!String.prototype.indexOfCaseInsensitive) {
	String.prototype.indexOfCaseInsensitive = function(search, from) {
		var string = (typeof from == "undefined") ? this : this.substring(from, this.length);
		var result = (typeof search == "string") ? new RegExp(search.replace(new RegExp("(\\W)", "g"), "\\$1"), "i").exec(string) : search.exec(string);
		return result ? result.index + ((typeof from == "number") ? from : 0) : -1;
	}
}

function getTagChunks(string, tagName, callback) {
	var chunks = new Array();
	var pos1 = pos2 = 0;
	while((pos1 = string.indexOfCaseInsensitive(new RegExp("<" + tagName + "\\s", "i"), pos2)) > -1) {
		var chunk = "";
		do {
			if((pos2 = string.indexOfCaseInsensitive(new RegExp("</" + tagName, "i"), Math.max(pos1, pos2))) == -1) {
				return chunks;
			}
			pos2 += tagName.length + 3;
			chunk = string.substring(pos1, pos2);
		} while(chunk != "" && chunk.count(new RegExp("<" + tagName + "\\s", "gi")) != chunk.count(new RegExp("</" + tagName, "gi")));
		if(typeof callback == "function")
			chunk = callback(chunk);
		chunks[chunks.length] = chunk;
	}
	return chunks;
}

function toggleMoreLess(obj, num, txtMore, txtLess)
{
	oMore = document.getElementById('more' + num);
	oContent = document.getElementById('content' + num);

	if (txtMore.Length == 0) txtMore = 'more...';
	if (txtLess.Length == 0) txtLess = 'less...';

	if (oContent.style.display == 'none') {
		oContent.style.display = 'block';
		oMore.className = "moreless_top";
		obj.innerHTML = txtLess;

		oLess = document.createElement("P");
		oLess.id = "less" + num;
		oLess.className = "moreless_bottom";
		var txtMore2 = txtMore.replace(/&/g,'&amp;');
		var txtLess2 = txtLess.replace(/&/g,'&amp;');

		oLess.innerHTML = '<span style="cursor: pointer;" onclick="toggleMoreLess(this, \'' + num + '\', \'' + txtMore2 + '\', \'' + txtLess2 + '\'); return false;">' + txtLess + '<\/span>';

		after = oContent.nextSibling;
		oContent.parentNode.insertBefore(oLess, after);
	} else {
		oContent.style.display = 'none';
		oMore.className = "moreless_fold";
		oMore.childNodes[0].innerHTML = txtMore;

		oLess = document.getElementById('less' + num);
		oContent.parentNode.removeChild(oLess);
	}
}
function getParentByTagName(tag, obj)
{
	while (obj.tagName != tag.toUpperCase()) {
		obj = obj.parentNode;
	}
	return obj;
}
function removeItselfById(id) {
	document.getElementById(id).parentNode.removeChild(document.getElementById(id));
}
function getSelectedRadio(buttonGroup) {
   // returns the array number of the selected radio button or -1 if no button is selected
   if (buttonGroup[0]) { // if the button group is an array (one button is not an array)
	  for (var i=0; i<buttonGroup.length; i++) {
		 if (buttonGroup[i].checked) {
			return i
		 }
	  }
   } else {
	  if (buttonGroup.checked) { return 0; } // if the one button is checked, return zero
   }
   // if we get to this point, no radio button is selected
   return -1;
} // Ends the "getSelectedRadio" function
function getSelectedRadioValue(buttonGroup) {
   // returns the value of the selected radio button or "" if no button is selected
   var i = getSelectedRadio(buttonGroup);
   if (i == -1) {
	  return "";
   } else {
	  if (buttonGroup[i]) { // Make sure the button group is an array (not just one button)
		 return buttonGroup[i].value;
	  } else { // The button group is just the one button, and it is checked
		 return buttonGroup.value;
	  }
   }
} // Ends the "getSelectedRadioValue" function
function showMessage(str) {
	PM.showMessage("" + str, "right", "bottom");
}
function preventEnter(event, onEnter) {
	if (!event) event = window.event;
	if (event.keyCode == 13) {
		event.returnValue = false;
		event.cancelBubble = true;
		try {
			event.preventDefault();
		} catch(e) { }
		if(typeof(onEnter) == "function")
			onEnter();
		return false;
	}
	return true;
}
function thisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName]
	}
	else {
		return document[movieName]
	}
}

function onClipBoard(result) {
	alert(result ? s_trackbackUrlCopied : s_trackbackUrlCopyFailed);
}

function gotoURL(url,target) {
	if(url) {
		window.open(url, target);
	}
}
function isColorRGB(string){
	var RGB3regex = /[0-9abcdef]{3}/i;
	var RGB6regex = /[0-9abcdef]{6}/i;
	if(string.length==3)
		return string.search(RGB3regex)>=0;
	if(string.length==6)
		return string.search(RGB6regex)>=0;
	return false;
}
function isNumber(string,minValue,maxValue){
	var RegEx =/^(0|-?[1-9][0-9]{0,9})$/;
	if(string.search(RegEx)<0)
		return false;
	if(maxValue == null )
		maxValue = 2147483646;
	if(minValue == null )
		minValue = -2147483647;
	if(parseInt(string)<minValue || parseInt(string)>maxValue)
		return false;
	return true;
}

var tistoryFootnote = {
	notes: {},

	add: function(entryId, noteId, content) {
		this.notes[entryId + '_' + noteId] = content;
	},

	show: function(anchor, entryId, noteId) {
		var layer = document.createElement('div');
		layer.id = 'tistoryFootnoteLayer_' + entryId + '_' + noteId;
		layer.style.font = '11px/1 Dotum, Sans-serif';
		layer.style.width = '304px';
		layer.style.position = 'absolute';
		layer.style.left = (getOffsetLeft(anchor) - 86) + 'px';
		layer.style.top = '0px';
		layer.style.zIndex = '810302';
		if(new RegExp('MSIE [1-6]\\.').test(navigator.userAgent)) {
			layer.innerHTML =
			'<div style="height: 6px; overflow: hidden; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'http://i1.daumcdn.net/cfs.tistory/static/admin/editor/footnotes_01.png\', sizingMethod=\'crop\')"></div><div style="width: 304px; padding: 6px 0; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'http://i1.daumcdn.net/cfs.tistory/static/admin/editor/footnotes_02.png\', sizingMethod=\'scale\')"><div style="padding-bottom: 6px; border-bottom: 1px solid #f2f1be; font-weight: bold; margin: 0 15px 9px 15px">각주 ' + noteId + '</div><div style="margin: 0 15px; line-height: 1.5">' +
				this.notes[entryId + '_' + noteId] +
			'</div></div><div style="height: 15px; overflow: hidden; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'http://i1.daumcdn.net/cfs.tistory/static/admin/editor/footnotes_03.png\', sizingMethod=\'crop\')"></div>';
		}
		else {
			layer.innerHTML =
			'<div style="height: 6px; overflow: hidden; background-image: url(http://i1.daumcdn.net/cfs.tistory/static/admin/editor/footnotes_01.png)"></div><div style="width: 304; padding: 6px 0; background-image: url(http://i1.daumcdn.net/cfs.tistory/static/admin/editor/footnotes_02.png)"><div style="padding-bottom: 6px; border-bottom: 1px solid #f2f1be; font-weight: bold; margin: 0 15px 9px 15px">각주 ' + noteId + '</div><div style="margin: 0 15px; line-height: 1.5; overflow: hidden">' +
				this.notes[entryId + '_' + noteId] +
			'</div></div><div style="height: 15px; overflow: hidden; background-image: url(http://i1.daumcdn.net/cfs.tistory/static/admin/editor/footnotes_03.png)"></div>';
		}
		document.body.appendChild(layer);
		layer.style.top = (getOffsetTop(anchor) - layer.offsetHeight) + 'px';
	},

	hide: function(entryId, noteId) {
		var layer = document.getElementById('tistoryFootnoteLayer_' + entryId + '_' + noteId);
		layer.parentNode.removeChild(layer);
	}
};

var tistoryCcl = {
	height: {
		1: 63,
		2: 63,
		3: 63,
		4: 77,
		5: 77,
		6: 63
	},

	show: function(anchor, type) {
		var layer = document.createElement('div');
		layer.id = 'tistoryCclLayer';
		layer.style.width = '99px';
		layer.style.height = this.height[type] + 'px';
		layer.style.position = 'absolute';
		layer.style.left = (getOffsetLeft(anchor) - 48) + 'px';
		layer.style.top = (getOffsetTop(anchor) - this.height[type]) + 'px';
		layer.style.zIndex = '810302';
		if(new RegExp('MSIE [1-6]\\.').test(navigator.userAgent))
			layer.style.filter = 'progid:DXImageTransform.Microsoft.AlphaImageLoader(src="http://i1.daumcdn.net/cfs.tistory/static/admin/editor/ccl_info0' + type + '.png", sizingMethod="crop")';
		else
			layer.style.backgroundImage = 'url(http://i1.daumcdn.net/cfs.tistory/static/admin/editor/ccl_info0' + type + '.png)';
		document.body.appendChild(layer);
	},

	hide: function() {
		var layer = document.getElementById('tistoryCclLayer');
		layer.parentNode.removeChild(layer);
	}
};

/* toggle elements: blog */
var clickAreaCheck = false;
document.onclick = function() {
	if (!clickAreaCheck) {
		divDisplay(old_div, 'none');
	}
	else {
		clickAreaCheck = false;
	}
}

var old_div = '';
function divDisplay(objId, act, check) {
	clickAreaCheck = true;
	if(old_div != '' && document.getElementById(old_div)){
		document.getElementById(old_div).style.display = 'none';
	}
	if (objId != '' && document.getElementById(objId)) {
		document.getElementById(objId).style.display = act;
	}
	if(check == true){
		old_div = objId;
	}
}

function toggleSetDefaultAdmin(curMode, Tedition){
		if (curMode == '/admin') {
			if (Tedition) {
				document.location.href = '/admin/display/tedition/';
			} else {
				document.location.href = "/admin";
			}
		} else if (confirm("새관리로 이동하시면, 새관리가 기본모드로 설정됩니다.\n새관리를 기본모드로 설정하시겠습니까?")) {
			setDefaultAdmin('/admin', Tedition);
		}
}

function setDefaultAdmin(mode, Tedition){
	var response = requestPostText("/admin/setting/setDefaultAdmin", "defaultAdmin="+mode);
	var result = eval("(" + response + ')');;

	if (result.success === true) {
		defaultAdmin = '/admin';
		if (confirm(result.desc + "\n기본 관리자 모드로 이동하시겠습니까?"))
			if (Tedition) {
				document.location.href = '/admin/display/tedition/';
			} else {
				document.location.href = mode;
			}
	}
}

function setPngFilterIE() {
	if(/MSIE [0-6]\./.test(navigator.userAgent)) {
		var imgCount = document.getElementsByName('flickrImg');
		var arrPng = [], imgSrc;

		for (var i=0, l=imgCount.length; i < l; i++ ) {
			if (imgSrc = imgCount[i].src.length > 0) {
				if (imgCount[i].src.substring(imgSrc - 3, imgSrc) == 'png') {
					arrPng.push(imgCount[i].readAttribute('id'));
				}
			}
		}

		for (var j = 0, k = arrPng.length; j < k; j++){
			var el = document.getElementById('flickrImg-' + j);
			el.style.filter='progid:DXImageTransform.Microsoft.AlphaImageLoader(src="'+el.src+'",sizingMethod="image")';
			el.src='http://i1.daumcdn.net/cfs.tistory/static/admin/form/s.gif';
		}
	}
}

//댓글 글자수 체크하기: from widgetbank
var checkCharLength = {
	config: {
		curValue: '',
		limitLength: 0,
		formId: '',
		outTextId: '',
		timer: null
	},

	init: function(limit_length, fId, txtId, isBgImg) {
		this.config.limitLength = limit_length;
		this.config.formId = fId;
		this.config.outTextId = txtId;

		this.start();
	},

	clearTimer: function() {
		if (this.config.timer != null && this.config.timer != 'undefined') {
			clearTimeout(this.config.timer);
			this.config.timer = null;
		}
	},

	start: function(){
		try {
			var comment = document.getElementById(this.config.formId);
			var length = calculate_msglen(comment.value);

			if (this.config.curValue != comment.value) {
				this.config.curValue = comment.value;
				document.getElementById(this.config.outTextId).innerHTML = length;
				if (length > this.config.limitLength) {
					alert("최대 " + this.config.limitLength + "byte이므로 초과된 글자수는 자동으로 삭제됩니다.");
					comment.value = comment.value.replace(/\r\n$/, "");
					comment.value = assert_msglen(comment.value, this.config.limitLength, this.config.outTextId);
				}
				this.config.timer = setTimeout("checkCharLength.start()", 10);
			}
		}
		catch (e) {
		}
	}
};

//글자수 알아내기: from widgetbank
function calculate_msglen(message){
	var nbytes = 0;
	for (var i=0; i<message.length; i++) {
		var ch = message.charAt(i);
		if(escape(ch).length > 4) {
			nbytes += 2;
		} else if (ch == '\n') {
			if (message.charAt(i-1) != '\r') {
				nbytes += 1;
			}
		} else if (ch == '<' || ch == '>') {
			nbytes += 4;
		} else {
			nbytes += 1;
		}
	}
	return nbytes;
}

//글자수 보여주고 이상이면 자르기: from widgetbank
function assert_msglen(message, maximum, textlimit){
	var inc = 0;
	var nbytes = 0;
	var msg = "";
	var msglen = message.length;

	for (var i=0; i<msglen; i++) {
		var ch = message.charAt(i);
		if (escape(ch).length > 4) {
			inc = 2;
		} else if (ch == '\n') {
			if (message.charAt(i-1) != '\r') {
				inc = 1;
			}
		} else if (ch == '<' || ch == '>') {
			inc = 4;
		} else {
			inc = 1;
		}
		if ((nbytes + inc) > maximum) {
			break;
		}
		nbytes += inc;
		msg += ch;
	}
	document.getElementById(textlimit).innerHTML = nbytes;
	return msg;
}

//Daum music 플레이어 비활성화
function playerControl(id, action) {
	if (action && action == 'add') {
		daumMusicPlayerList.push(id);
	} else {
		var pidA = daumMusicPlayerList;
		for(var i = 0, l = pidA.length; i < l; i++) {
	        if(id != pidA[i]){  //플래쉬에서 호출한 id와 다른 플래이어는 모두 비활성
	             document.getElementById(pidA[i]).passiveMusic();   //플래쉬안에 등록된 함수 passiveMusic
	        }
		}
	}
}

function setCookie(name, value, days, domain) {
	var expires = "";
	var _domain = '';
	if(days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 86400000));
		expires = "; expires=" + date.toGMTString();
	}
	if(domain) {
		_domain = 'domain='+domain
	}
	document.cookie = name + "=" + value + expires + "; path=/;"+_domain;
}

function goMobilePage(url, domain){
	var domain = domain || '';
	setCookie('M_P2M', 'Y', '', domain);
	window.location.href=url;
}

function moveCategoryPaging(action, category) {
	setCookie("categoryNo",category);
	location.href = action;
}
//]]>