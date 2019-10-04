//# sourceMappingURL=app.js.map
var App=function(){var b,l,m,r,t,f,u,k,A=function(){b=$("#page-container");l=$("#page-content");m=$("header");r=$("#page-content + footer");t=$("#sidebar");f=$("#sidebar-scroll");u=$("#sidebar-alt");k=$("#sidebar-alt-scroll");e("init");v();w();x();y();n();$(window).resize(function(){n()});$(window).bind("orientationchange",n);var a=$("#year-copy"),c=new Date;2014===c.getFullYear()?a.html("2014"):a.html("2014-"+c.getFullYear().toString().substr(2,2));z();$('[data-toggle="tabs"] a, .enable-tabs a').click(function(a){a.preventDefault();
$(this).tab("show")});$('[data-toggle="tooltip"], .enable-tooltip').tooltip({container:"body",animation:!1});$('[data-toggle="popover"], .enable-popover').popover({container:"body",animation:!0});$('[data-toggle="lightbox-image"]').magnificPopup({type:"image",image:{titleSrc:"title"}});$('[data-toggle="lightbox-gallery"]').each(function(){$(this).magnificPopup({delegate:"a.gallery-link",type:"image",gallery:{enabled:!0,navigateByImgClick:!0,arrowMarkup:'<button type="button" class="mfp-arrow mfp-arrow-%dir%" title="%title%"></button>',
tPrev:"Previous",tNext:"Next",tCounter:'<span class="mfp-counter">%curr% of %total%</span>'},image:{titleSrc:"title"}})});$(".input-typeahead").typeahead({source:"Afghanistan;Albania;Algeria;American Samoa;Andorra;Angola;Anguilla;Antarctica;Antigua and Barbuda;Argentina;Armenia;Aruba;Australia;Austria;Azerbaijan;Bahrain;Bangladesh;Barbados;Belarus;Belgium;Belize;Benin;Bermuda;Bhutan;Bolivia;Bosnia and Herzegovina;Botswana;Bouvet Island;Brazil;British Indian Ocean Territory;British Virgin Islands;Brunei;Bulgaria;Burkina Faso;Burundi;C\u0393\u0384te d'Ivoire;Cambodia;Cameroon;Canada;Cape Verde;Cayman Islands;Central African Republic;Chad;Chile;China;Christmas Island;Cocos (Keeling) Islands;Colombia;Comoros;Congo;Cook Islands;Costa Rica;Croatia;Cuba;Cyprus;Czech Republic;Democratic Republic of the Congo;Denmark;Djibouti;Dominica;Dominican Republic;East Timor;Ecuador;Egypt;El Salvador;Equatorial Guinea;Eritrea;Estonia;Ethiopia;Faeroe Islands;Falkland Islands;Fiji;Finland;Former Yugoslav Republic of Macedonia;France;French Guiana;French Polynesia;French Southern Territories;Gabon;Georgia;Germany;Ghana;Gibraltar;Greece;Greenland;Grenada;Guadeloupe;Guam;Guatemala;Guinea;Guinea-Bissau;Guyana;Haiti;Heard Island and McDonald Islands;Honduras;Hong Kong;Hungary;Iceland;India;Indonesia;Iran;Iraq;Ireland;Israel;Italy;Jamaica;Japan;Jordan;Kazakhstan;Kenya;Kiribati;Kuwait;Kyrgyzstan;Laos;Latvia;Lebanon;Lesotho;Liberia;Libya;Liechtenstein;Lithuania;Luxembourg;Macau;Madagascar;Malawi;Malaysia;Maldives;Mali;Malta;Marshall Islands;Martinique;Mauritania;Mauritius;Mayotte;Mexico;Micronesia;Moldova;Monaco;Mongolia;Montserrat;Morocco;Mozambique;Myanmar;Namibia;Nauru;Nepal;Netherlands;Netherlands Antilles;New Caledonia;New Zealand;Nicaragua;Niger;Nigeria;Niue;Norfolk Island;North Korea;Northern Marianas;Norway;Oman;Pakistan;Palau;Panama;Papua New Guinea;Paraguay;Peru;Philippines;Pitcairn Islands;Poland;Portugal;Puerto Rico;Qatar;R\u0393\u00a9union;Romania;Russia;Rwanda;S\u0393\u00a3o Tom\u0393\u00a9 and Pr\u0393\u00adncipe;Saint Helena;Saint Kitts and Nevis;Saint Lucia;Saint Pierre and Miquelon;Saint Vincent and the Grenadines;Samoa;San Marino;Saudi Arabia;Senegal;Seychelles;Sierra Leone;Singapore;Slovakia;Slovenia;Solomon Islands;Somalia;South Africa;South Georgia and the South Sandwich Islands;South Korea;Spain;Sri Lanka;Sudan;Suriname;Svalbard and Jan Mayen;Swaziland;Sweden;Switzerland;Syria;Taiwan;Tajikistan;Tanzania;Thailand;The Bahamas;The Gambia;Togo;Tokelau;Tonga;Trinidad and Tobago;Tunisia;Turkey;Turkmenistan;Turks and Caicos Islands;Tuvalu;US Virgin Islands;Uganda;Ukraine;United Arab Emirates;United Kingdom;United States;United States Minor Outlying Islands;Uruguay;Uzbekistan;Vanuatu;Vatican City;Venezuela;Vietnam;Wallis and Futuna;Western Sahara;Yemen;Yugoslavia;Zambia;Zimbabwe".split(";")});
$(".select-chosen").chosen({width:"100%"});$(".select-select2").select2();$(".input-colorpicker").colorpicker({format:"hex"});$(".input-colorpicker-rgba").colorpicker({format:"rgba"});$(".input-slider").slider();$(".input-tags").tagsInput({width:"auto",height:"auto"});$(".input-datepicker, .input-daterange").datepicker({weekStart:1});$(".input-datepicker-close").datepicker({weekStart:1}).on("changeDate",function(a){$(this).datepicker("hide")});$(".input-timepicker").timepicker({minuteStep:1,showSeconds:!0,
showMeridian:!0});$(".input-timepicker24").timepicker({minuteStep:1,showSeconds:!0,showMeridian:!1});$(".pie-chart").easyPieChart({barColor:$(this).data("bar-color")?$(this).data("bar-color"):"#777777",trackColor:$(this).data("track-color")?$(this).data("track-color"):"#eeeeee",lineWidth:$(this).data("line-width")?$(this).data("line-width"):3,size:$(this).data("size")?$(this).data("size"):"80",animate:800,scaleColor:!1});$("input, textarea").placeholder()},q=function(){return window.innerWidth||document.documentElement.clientWidth||
document.body.clientWidth},v=function(){var a=$(".sidebar-nav-menu"),c=$(".sidebar-nav-submenu");a.click(function(){var a=$(this);b.hasClass("sidebar-mini")&&b.hasClass("sidebar-visible-lg-mini")&&991<q()?a.hasClass("open")?a.removeClass("open"):($(".sidebar-nav-menu.open").removeClass("open"),a.addClass("open")):a.parent().hasClass("active")||(a.hasClass("open")?a.removeClass("open").next().slideUp(250,function(){p(a,200,300)}):($(".sidebar-nav-menu.open").removeClass("open").next().slideUp(250),
a.addClass("open").next().slideDown(250,function(){p(a,150,600)})),setTimeout(n,250));a.blur();return!1});c.click(function(){var a=$(this);!0!==a.parent().hasClass("active")&&(a.hasClass("open")?a.removeClass("open").next().slideUp(250,function(){p(a,200,300)}):(a.closest("ul").find(".sidebar-nav-submenu.open").removeClass("open").next().slideUp(250),a.addClass("open").next().slideDown(250,function(){p(a,150,600)})),setTimeout(n,250));a.blur();return!1})},p=function(a,c,d){if(!b.hasClass("disable-menu-autoscroll"))if(m.hasClass("navbar-fixed-top")||
m.hasClass("navbar-fixed-bottom")){var g=a.parents("#sidebar-scroll");a=a.offset().top+Math.abs($("div:first",g).offset().top);g.animate({scrollTop:0<a-c?a-c:0},d)}else g=a.offset().top,c=0<g-c?g-c:0,$("html, body").animate({scrollTop:c},d)},e=function(a,c){if("init"===a)e("sidebar-scroll"),e("sidebar-alt-scroll"),$(".sidebar-partial #sidebar").mouseenter(function(){e("close-sidebar-alt")}),$(".sidebar-alt-partial #sidebar-alt").mouseenter(function(){e("close-sidebar")});else{var d=q();if("toggle-sidebar"===
a)991<d?(b.toggleClass("sidebar-visible-lg"),b.hasClass("sidebar-mini")&&b.toggleClass("sidebar-visible-lg-mini"),b.hasClass("sidebar-visible-lg")&&e("close-sidebar-alt"),"toggle-other"===c&&(b.hasClass("sidebar-visible-lg")||e("open-sidebar-alt"))):(b.toggleClass("sidebar-visible-xs"),b.hasClass("sidebar-visible-xs")&&e("close-sidebar-alt")),e("sidebar-scroll");else if("toggle-sidebar-alt"===a)991<d?(b.toggleClass("sidebar-alt-visible-lg"),b.hasClass("sidebar-alt-visible-lg")&&e("close-sidebar"),
"toggle-other"===c&&(b.hasClass("sidebar-alt-visible-lg")||e("open-sidebar"))):(b.toggleClass("sidebar-alt-visible-xs"),b.hasClass("sidebar-alt-visible-xs")&&e("close-sidebar"));else if("open-sidebar"===a)991<d?(b.hasClass("sidebar-mini")&&b.removeClass("sidebar-visible-lg-mini"),b.addClass("sidebar-visible-lg")):b.addClass("sidebar-visible-xs"),e("close-sidebar-alt");else if("open-sidebar-alt"===a)991<d?b.addClass("sidebar-alt-visible-lg"):b.addClass("sidebar-alt-visible-xs"),e("close-sidebar");
else if("close-sidebar"===a)991<d?(b.removeClass("sidebar-visible-lg"),b.hasClass("sidebar-mini")&&b.addClass("sidebar-visible-lg-mini")):b.removeClass("sidebar-visible-xs");else if("close-sidebar-alt"===a)991<d?b.removeClass("sidebar-alt-visible-lg"):b.removeClass("sidebar-alt-visible-xs");else if("sidebar-scroll"===a)if(b.hasClass("sidebar-mini")&&b.hasClass("sidebar-visible-lg-mini")&&991<d)f.length&&f.parent(".slimScrollDiv").length&&(f.slimScroll({destroy:!0}),f.attr("style",""));else{if(b.hasClass("header-fixed-top")||
b.hasClass("header-fixed-bottom"))if(a=$(window).height(),f.length&&!f.parent(".slimScrollDiv").length){f.slimScroll({height:a,color:"#fff",size:"3px",touchScrollStep:100});var g;$(window).on("resize orientationchange",function(){clearTimeout(g);g=setTimeout(function(){e("sidebar-scroll")},150)})}else f.add(f.parent()).css("height",a)}else if("sidebar-alt-scroll"===a&&(b.hasClass("header-fixed-top")||b.hasClass("header-fixed-bottom")))if(a=$(window).height(),k.length&&!k.parent(".slimScrollDiv").length){k.slimScroll({height:a,
color:"#fff",size:"3px",touchScrollStep:100});var h;$(window).on("resize orientationchange",function(){clearTimeout(h);h=setTimeout(function(){e("sidebar-alt-scroll")},150)})}else k.add(k.parent()).css("height",a)}return!1},n=function(){var a=$(window).height(),c=t.outerHeight(),d=u.outerHeight(),g=m.outerHeight(),e=r.outerHeight();m.hasClass("navbar-fixed-top")||m.hasClass("navbar-fixed-bottom")||c<a&&d<a?b.hasClass("footer-fixed")?l.css("min-height",a-g+"px"):l.css("min-height",a-(g+e)+"px"):b.hasClass("footer-fixed")?
l.css("min-height",(c>d?c:d)-g+"px"):l.css("min-height",(c>d?c:d)-(g+e)+"px")},w=function(){$('[data-toggle="block-toggle-content"]').on("click",function(){var a=$(this).closest(".block").find(".block-content");$(this).hasClass("active")?a.slideDown():a.slideUp();$(this).toggleClass("active")});$('[data-toggle="block-toggle-fullscreen"]').on("click",function(){var a=$(this).closest(".block");$(this).hasClass("active")?a.removeClass("block-fullscreen"):a.addClass("block-fullscreen");$(this).toggleClass("active")});
$('[data-toggle="block-hide"]').on("click",function(){$(this).closest(".block").fadeOut()})},x=function(){var a=$("#to-top");$(window).scroll(function(){150<$(this).scrollTop()&&991<q()?a.fadeIn(100):a.fadeOut(100)});a.click(function(){$("html, body").animate({scrollTop:0},400);return!1})},z=function(){var a=$(".chat-users"),b=$(".chat-talk"),d=$(".chat-talk-messages"),e=$("#sidebar-chat-message"),h="";$(".chat-talk-messages").slimScroll({height:210,color:"#fff",size:"3px",position:"left",touchScrollStep:100});
$("a",a).click(function(){a.slideUp();b.slideDown();e.focus();return!1});$("#chat-talk-close-btn").click(function(){b.slideUp();a.slideDown();return!1});$("#sidebar-chat-form").submit(function(a){if(h=e.val())d.append('<li class="chat-talk-msg chat-talk-msg-highlight themed-border animation-slideLeft">'+$("<div />").text(h).html()+"</li>"),d.animate({scrollTop:d[0].scrollHeight},500),e.val("");a.preventDefault()})},y=function(){var a=$(".sidebar-themes"),c=$("#theme-link"),d=c.length?c.attr("href"):
"default",e=b.hasClass("enable-cookies")?!0:!1,h;if(e){if(h=Cookies.get("optionThemeColor")?Cookies.get("optionThemeColor"):!1)"default"===h?c.length&&(c.remove(),c=$("#theme-link")):c.length?c.attr("href",h):($('link[href="css/themes.css"]').before('<link id="theme-link" rel="stylesheet" href="'+h+'">'),c=$("#theme-link"));d=h?h:d}$('a[data-theme="'+d+'"]',a).parent("li").addClass("active");$("a",a).click(function(b){d=$(this).data("theme");$("li",a).removeClass("active");$(this).parent("li").addClass("active");
"default"===d?c.length&&(c.remove(),c=$("#theme-link")):c.length?c.attr("href",d):($('link[href="css/themes.css"]').before('<link id="theme-link" rel="stylesheet" href="'+d+'">'),c=$("#theme-link"));e&&Cookies.set("optionThemeColor",d,{expires:7})});$(".dropdown-options a").click(function(a){a.stopPropagation()});var f=$("#options-main-style"),k=$("#options-main-style-alt");b.hasClass("style-alt")?k.addClass("active"):f.addClass("active");f.click(function(){b.removeClass("style-alt");$(this).addClass("active");
k.removeClass("active")});k.click(function(){b.addClass("style-alt");$(this).addClass("active");f.removeClass("active")});var l=$("#options-header-default"),n=$("#options-header-inverse");m.hasClass("navbar-default")?l.addClass("active"):n.addClass("active");l.click(function(){m.removeClass("navbar-inverse").addClass("navbar-default");$(this).addClass("active");n.removeClass("active")});n.click(function(){m.removeClass("navbar-default").addClass("navbar-inverse");$(this).addClass("active");l.removeClass("active")})};
return{init:function(){A();var a=$("#page-wrapper");a.hasClass("page-loading")&&a.removeClass("page-loading")},sidebar:function(a,b){e(a,b)},datatables:function(){$.extend(!0,$.fn.dataTable.defaults,{sDom:"<'row'<'col-sm-6 col-xs-5'l><'col-sm-6 col-xs-7'f>r>t<'row'<'col-sm-5 hidden-xs'i><'col-sm-7 col-xs-12 clearfix'p>>",sPaginationType:"bootstrap",oLanguage:{sLengthMenu:"_MENU_",sSearch:'<div class="input-group">_INPUT_<span class="input-group-addon"><i class="fa fa-search"></i></span></div>',sInfo:"<strong>_START_</strong>-<strong>_END_</strong> of <strong>_TOTAL_</strong>",
oPaginate:{sPrevious:"",sNext:""}}});$.extend($.fn.dataTableExt.oStdClasses,{sWrapper:"dataTables_wrapper form-inline",sFilterInput:"form-control",sLengthSelect:"form-control"})},pagePrint:function(){var a=b.prop("class");b.prop("class","");window.print();b.prop("class",a)}}}();$(function(){App.init()});