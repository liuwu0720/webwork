<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <title>康舶司App用户服务协议</title>
<style>
<!--
 /* Font Definitions */
@font-face
	{font-family:"Courier New";
	panose-1:2 7 3 9 2 2 5 2 4 4;
	mso-font-charset:0;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 0 0 0 1 0;}
@font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;
	mso-font-charset:2;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:0 268435456 0 0 -2147483648 0;}
@font-face
	{font-family:宋体;
	mso-font-alt:宋体;
	mso-font-charset:80;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:宋体;
	mso-font-alt:宋体;
	mso-font-charset:80;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:微软雅黑;
	mso-font-alt:"Arial Unicode MS";
	mso-font-charset:134;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:-2147483001 705641554 22 0 262175 0;}
 /* Style Definitions */
p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-unhide:no;
	mso-style-qformat:yes;
	mso-style-parent:"";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:10.5pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{mso-style-unhide:no;
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	tab-stops:center 207.65pt right 415.3pt;
	layout-grid-mode:char;
	border:none;
	mso-border-alt:none windowtext 0cm;
	padding:0cm;
	mso-padding-alt:1.0pt 4.0pt 1.0pt 4.0pt;
	font-size:9.0pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{mso-style-unhide:no;
	margin:0cm;
	margin-bottom:.0001pt;
	mso-pagination:none;
	tab-stops:center 207.65pt right 415.3pt;
	layout-grid-mode:char;
	font-size:9.0pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
.MsoChpDefault
	{mso-style-type:export-only;
	mso-default-props:yes;
	mso-fareast-font-family:宋体;}
 /* Page Definitions */
@page
	{mso-page-border-surround-header:no;
	mso-page-border-surround-footer:no;
	mso-footnote-separator:url(":\7528\6237\534F\8BAE2_files:header.htm") fs;
	mso-footnote-continuation-separator:url(":\7528\6237\534F\8BAE2_files:header.htm") fcs;
	mso-endnote-separator:url(":\7528\6237\534F\8BAE2_files:header.htm") es;
	mso-endnote-continuation-separator:url(":\7528\6237\534F\8BAE2_files:header.htm") ecs;}
@page WordSection1
	{size:595.3pt 841.9pt;
	margin:72.0pt 90.0pt 72.0pt 90.0pt;
	mso-header-margin:42.55pt;
	mso-footer-margin:49.6pt;
	mso-paper-source:0;
	layout-grid:15.6pt;}
div.WordSection1
	{page:WordSection1;}
 /* List Definitions */
@list l0
	{mso-list-id:-227;
	mso-list-template-ids:1074553392;}
@list l0:level1
	{mso-level-number-format:bullet;
	mso-level-text:"";
	mso-level-tab-stop:0cm;
	mso-level-number-position:left;
	margin-left:0cm;
	text-indent:0cm;
	font-family:Symbol;}
@list l0:level2
	{mso-level-number-format:bullet;
	mso-level-text:;
	mso-level-tab-stop:36.0pt;
	mso-level-number-position:left;
	margin-left:54.0pt;
	text-indent:-18.0pt;
	font-family:Symbol;}
@list l0:level3
	{mso-level-number-format:bullet;
	mso-level-text:o;
	mso-level-tab-stop:72.0pt;
	mso-level-number-position:left;
	margin-left:90.0pt;
	text-indent:-18.0pt;
	font-family:"Courier New";}
@list l0:level4
	{mso-level-number-format:bullet;
	mso-level-text:;
	mso-level-tab-stop:108.0pt;
	mso-level-number-position:left;
	margin-left:126.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l0:level5
	{mso-level-number-format:bullet;
	mso-level-text:;
	mso-level-tab-stop:144.0pt;
	mso-level-number-position:left;
	margin-left:162.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l0:level6
	{mso-level-number-format:bullet;
	mso-level-text:;
	mso-level-tab-stop:180.0pt;
	mso-level-number-position:left;
	margin-left:198.0pt;
	text-indent:-18.0pt;
	font-family:Symbol;}
@list l0:level7
	{mso-level-number-format:bullet;
	mso-level-text:o;
	mso-level-tab-stop:216.0pt;
	mso-level-number-position:left;
	margin-left:234.0pt;
	text-indent:-18.0pt;
	font-family:"Courier New";}
@list l0:level8
	{mso-level-number-format:bullet;
	mso-level-text:;
	mso-level-tab-stop:252.0pt;
	mso-level-number-position:left;
	margin-left:270.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l0:level9
	{mso-level-number-format:bullet;
	mso-level-text:;
	mso-level-tab-stop:288.0pt;
	mso-level-number-position:left;
	margin-left:306.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
ol
	{margin-bottom:0cm;}
ul
	{margin-bottom:0cm;}
-->
</style>
<!--[if gte mso 10]>
<style>
 /* Style Definitions */
table.MsoNormalTable
	{mso-style-name:"Table Normal";
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-parent:"";
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-para-margin:0cm;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.0pt;
	font-family:"Times New Roman";}
</style>
<![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="3074"/>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
</head>

<body bgcolor=white lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:
punctuation'>

<div class=WordSection1 style='layout-grid:15.6pt'>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-bottom:15.0pt;text-indent:21.0pt;line-height:
15.0pt;background:#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;
text-autospace:ideograph-other'><span style='font-size:9.0pt;mso-bidi-font-size:
10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;background:white'>请仔细阅读本协议，康舶司App平台将依据以下条件和条款为您提供服务。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='margin-bottom:15.0pt;text-indent:21.0pt;line-height:
15.0pt;background:#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;
text-autospace:ideograph-other'><span style='font-size:9.0pt;mso-bidi-font-size:
10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;background:white'>欢迎阅读康舶司App平台用户协议<span
lang=EN-US>(</span>下称<span lang=EN-US>“</span>本协议<span lang=EN-US>”)</span>。本协议阐述之条款和条件适用于您使用康舶司App平台所提供的各种工具和服务<span
lang=EN-US>(</span>下称<span lang=EN-US>“</span>服务<span lang=EN-US>”)<o:p></o:p></span></span></p>

<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>1</span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>．服务条款的确认</span></b><span lang=EN-US
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>康舶司App平台根据本服务条款及对该条款的修改向用户提供服务。本服务条款具有合同法上的法律效力。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>如果您对协议的任何条款表示异议，您可以选择不注册，一旦您点选<span lang=EN-US>“</span>注册<span
lang=EN-US>”</span>并通过注册程序，即表示您自愿接受本协议之所有条款，并已成为康舶司App平台的注册会员。用户在使用康舶司App平台的同时，同意接受康舶司App平台会员服务提供的各类信息服务。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>2</span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>．服务内容及修改、中断、终止</span></b><span lang=EN-US
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.1 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台服务的具体内容由本平台根据实际情况提供，并对所提供之服务拥有最终解释权。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台仅向其会员提供相关服务，与相关服务有关的设备（如个人电脑、手机、及其他与接入互联网或移动网有关的装置）及所需的费用（如为接入互联网而支付的电话费及上网费、为使用移动网而支付的手机费）均由会员自行负担。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.3 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>鉴于网络服务的特殊性，用户同意康舶司App平台有权不经事先通知，随时变更、中断或终止部分或全部的网络服务（包括收费网络服务）。康舶司App平台不担保网络服务不会中断，对网络服务的及时性、安全性、准确性也都不作担保。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.4 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台需要定期或不定期地对提供网络服务的平台或相关的设备进行检修或者维护，如因此类情况而造成网络服务（包括收费网络服务）在合理时间内的中断，康舶司App平台无需为此承担任何责任。康舶司App平台保留不经事先通知为维修保养、升级或其它目的暂停全部或部分的网络服务的权利。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.5 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户明确同意其使用康舶司App平台网络服务所存在的风险将完全由其自己承担。用户理解并接受下载或通过康舶司App平台服务而链接及得到的资讯、产品及服务均系康舶司App平台自动搜录，康舶司App平台对其合法性概不负责，亦不承担任何法律责任，用户自行承担风险，康舶司App平台不做任何形式的保证，不保证搜索结果满足用户的要求，不保证搜索服务不中断，对搜索结果的安全性、正确性、及时性、合法性均不做担保。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.6 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台对超过<span lang=EN-US>6</span>个月未登录使用的帐号，保留关闭的权利。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.7 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台有权于任何时间暂时或永久修改或终止本服务（或其任何部分），而无论其通知与否，康舶司App平台对用户和任何第三人均无需承担任何责任。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>2.8 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>您同意康舶司App平台得基于其自行之考虑，因任何理由，包含但不限于长时间未使用，或康舶司App平台认为您已经违反本服务协议的文字及精神，终止您的密码、帐号或本服务之使用（或服务之任何部分），并将您在本服务内任何内容加以移除并删除。您同意依本服务协议任何规定提供之服务，无需进行事先通知即可中断或终止，您承认并同意，康舶司App平台可立即关闭或删除您的帐号及您帐号中所有相关信息及文件，或禁止继续使用前述文件或本服务。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>此外，您同意若本服务之使用被中断或终止或您的帐号及相关信息和文件被关闭或删除，康舶司App平台对您或任何第三人均不承担任何责任。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>3</span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>．会员帐号及密码</span></b><span lang=EN-US
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>用户注册会员成功后，康舶司App平台将给予每个会员一个帐号及相应的密码，该帐号和密码由用户负责保管；用户应当对以其用户帐号进行的所有活动和事件负法律责任。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>因黑客行为或会员保管疏忽致使帐号、密码被他人非法使用的，本公司不承担任何责任。如您发现任何非法使用会员帐号或安全漏洞的情况，请立即与本公司联系。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>4. </span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>注册信息和隐私保护</span></b><span lang=EN-US
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>4.1 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台帐号（即康舶司App平台用户<span lang=EN-US>ID</span>）的所有权归康舶司App平台，用户完成注册申请手续后，获得康舶司App平台帐号的使用权。用户应提供及时、详尽及准确的个人资料，并不断更新注册资料，符合及时、详尽准确的要求。所有原始键入的资料将作为注册资料。如果因用户注册信息不真实而引起的问题及其产生的后果，康舶司App平台不负任何责任。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>4.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户不得将其帐号、密码转让或出借予他人使用。如发现其帐号遭他人非法使用，应立即通知康舶司App平台。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>4.3 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台不对外公开或向第三方提供单个用户的注册资料，除非：<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>• </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>事先获得用户的明确授权；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>• </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>只有透露你的个人资料，才能提供你所要求的产品和服务；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>• </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>根据有关的法律法规要求；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>• </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>按照相关政府主管部门的要求；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>• </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>为维护康舶司App平台的合法权益。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>4.4 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>在您注册康舶司App平台帐户，使用康舶司App平台产品或服务，或访问康舶司App平台网页时，康舶司App平台会收集您的个人身份识别资料，并会将这些资料用于：改进为您提供的服务及网页内容。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>5. </span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>用户权责</span></b><span lang=EN-US
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.1</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>符合下列条件之一的个人、组织，才能申请成为康舶司App平台用户、使用本协议项下的服务：<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.1.1 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>年满十八周岁，并具有民事权利能力和民事行为能力的自然人；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.1.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>未满十八周岁，但监护人（包括但不仅限于父母）予以书面同意的自然人；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.1.3 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>根据中国法律、法规成立并合法存在的公司等企业法人、事业单位、社团组织和其他组织。无民事行为能力人、限制民事行为能力人以及无经营或特定经营资格的组织不当注册为用户的，其与本公司之间的协议自始无效，本公司一经发现，有权立即注销该用户。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户有权按照康舶司App平台规定的程序和要求使用康舶司App平台向会员提供的各项网络服务，如果会员对该服务有异议，可以与康舶司App平台联系以便得到及时解决。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.3 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户在申请使用康舶司App平台网络服务时，必须向康舶司App平台提供准确的个人资料，如个人资料有任何变动，必须及时更新。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.4 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户须同意接受康舶司App平台通过电子邮件或其他方式向会员发送相关商业信息。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.5 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户在使用康舶司App平台服务时，必须遵守中华人民共和国相关法律法规的规定，不得利用本服务进行任何违法或不正当的活动，包括但不限于下列行为<span
lang=EN-US>∶<o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.5.1 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>制作、复制、发布、传播或以其它方式传送含有下列内容之一的信息：<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>反对宪法所确定的基本原则的<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>损害国家荣誉和利益的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>煽动民族仇恨、民族歧视、破坏民族团结的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>破坏国家宗教政策，宣扬邪教和封建迷信的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>散布谣言，扰乱社会秩序，破坏社会稳定的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>侮辱或者诽谤他人，侵害他人合法权利的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>煽动非法集会、结社、游行、示威、聚众扰乱社会秩序的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>以非法民间组织名义活动的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>含有虚假、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、猥亵、或其它道德上令人反感的内容；<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>含有中国法律、法规、规章、条例以及任何具有法律效力之规范所限制或禁止的其它内容的。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.5.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>不得利用康舶司App服务从事以下活动：<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>未经允许，进入计算机信息网络或者使用计算机信息网络资源；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>未经允许，对计算机信息网络功能进行删除、修改或者增加；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>未经允许，对进入计算机信息网络中存储、处理或者传输的数据和应用程序进行删除、修改或者增加；<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>故意制作、传播计算机病毒等破坏性程序；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:42.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>•</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>其他危害计算机信息网络安全的行为。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.6 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户违反本协议或相关的服务条款的规定，导致或产生的任何第三方主张的任何索赔、要求或损失，包括合理的律师费，您同意赔偿康舶司App平台与合作公司、关联公司，并使之免受损害。对此，康舶司App平台有权视用户的行为性质，采取包括但不限于删除用户发布信息内容、暂停使用许可、终止服务、限制使用、回收康舶司App平台帐号、追究法律责任等措施。对恶意注册康舶司App平台帐号或利用康舶司App平台帐号进行违法活动、捣乱、骚扰、欺骗、其他用户以及其他违反本协议的行为，康舶司App平台有权回收其帐号。同时，本公司会视司法部门的要求，协助调查。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.7 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户不得对康舶司App平台任何部分通过任何方式进行复制、拷贝、出售、转售或用于任何其它商业目的。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>5.8 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户须对自己在使用康舶司App平台服务过程中的行为承担法律责任。用户承担法律责任的形式包括但不限于：对受到侵害者进行赔偿，以及在康舶司App平台运营公司首先承担了因用户行为导致的行政处罚或侵权损害赔偿责任后，用户应给予康舶司App平台运营公司等额的赔偿。<span
lang=EN-US><o:p></o:p></span></span></p>


<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>6. </span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>知识产权</span></b><span lang=EN-US
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>6.1 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台网站及网站所使用的任何相关软件、程序、内容，包括但不限于作品、图片、档案、资料、网站构架、网站版面的安排、网页设计、经由本网站或广告商向用户呈现的广告或资讯，均由本公司或其它权利人依法享有相应的知识产权，包括但不限于著作权、商标权、专利权或其它专属权利等，受到相关法律的保护。未经本公司或权利人明示授权，用户保证不修改、出租、出借、出售、散布本网站及本网站所使用的上述任何资料和资源，或根据上述资料和资源制作成任何种类物品。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>6.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>本公司授予用户不可转移及非专属的使用权，使用户可以通过单机计算机使用本网站的目标代码（以下简称<span
lang=EN-US>“</span>软件<span lang=EN-US>”</span>），但用户不得且不得允许任何第三方，复制、修改、创作衍生作品、进行还原工程、反向组译，或以其它方式破译或试图破译源代码，或出售、转让<span
lang=EN-US>“</span>软件<span lang=EN-US>”</span>或对<span lang=EN-US>“</span>软件<span
lang=EN-US>”</span>进行再授权，或以其它方式移转<span lang=EN-US>“</span>软件<span lang=EN-US>”</span>之任何权利。用户同意不以任何方式修改<span
lang=EN-US>“</span>软件<span lang=EN-US>”</span>，或使用修改后的<span lang=EN-US>“</span>软件<span
lang=EN-US>”</span>。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>6.3 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台有权在所有由平台提供的功能界面标注“此功能由康舶司App平台提供”等相关版权信息。。<span lang=EN-US><o:p></o:p></span></span></p>


<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>6.4 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户不得经由非康舶司App平台所提供的界面使用本网站。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='line-height:15.0pt;background:#FEFEFE;mso-shading:
windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><b
style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>7. </span></b><b style='mso-bidi-font-weight:normal'><span
style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>其他</span></b><span lang=EN-US style='font-size:
9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'><o:p></o:p></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.1 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>因不可抗力或者其他意外事件，使得本协议的履行不可能、不必要或者无意义的，双方均不承担责任。本协议所称之不可抗力意指不能预见、不能避免并不能克服的客观情况，包括但不限于战争、台风、水灾、火灾、雷击或地震、罢工、暴动、法定疾病、黑客攻击、网络病毒、电信部门技术管制、政府行为或任何其它自然或人为造成的灾难等客观情况。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.2 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>本条款及其修订本的有效性、履行和与本条款及其修订本效力有关的所有事宜，将受中华人民共和国法律约束，任何争议仅适用中华人民共和国法律。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.3 </span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>因本条款所引起的用户与本公司的任何纠纷或争议，首先应友好协商解决，协商不成的，用户在此同意将纠纷或争议提交本公司住所地有管辖权的人民法院诉讼解决。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.4</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>用户正确提交注册程序所需的资料并确认本协议后，本协议在康舶司App平台与用户之间成立并生效。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.5</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>本协议生效前的注册用户只需要使用原用户名及密码登录康舶司App平台网站重新接受并确认本协议，即可继续使用康舶司App平台的服务。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.6</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>协议有效期：从用户同意本协议或使用康舶司App平台起至用户注销康舶司App平台服务止。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.7</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>本协议如无特殊规定，双方应当使用电子邮件方式就本协议相关事项进行联系。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.8</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>康舶司App平台各相关部门的电子邮箱以在康舶司App平台官方网站上注明的为准。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.9</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>使用康舶司App平台即表示接受康舶司App平台服务协议及其所有附件。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:21.0pt;line-height:15.0pt;background:
#FEFEFE;mso-shading:windowtext;mso-pattern:solid white;text-autospace:ideograph-other'><span
lang=EN-US style='font-size:9.0pt;mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";
color:#333333;background:white'>7.10</span><span style='font-size:9.0pt;
mso-bidi-font-size:10.0pt;font-family:"微软雅黑","sans-serif";color:#333333;
background:white'>本协议的最终解释权归康舶司App平台所有。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

</div>

</body>

</html>