!function(a){a.fn.nicefileinput=function(b){var c={label:"Browse...",fullPath:!1};return b&&a.extend(c,b),this.each(function(){var b=this;if(void 0===a(b).attr("data-styled")){var d=Math.round(1e4*Math.random()),e=new Date,f=e.getTime()+d.toString(),g=a('<input type="text" readonly="readonly">').css({display:"block","float":"left",margin:0,padding:"0 5px"}).addClass("NFI-filename NFI"+f),h=a("<div>").css({overflow:"hidden",position:"relative",display:"block","float":"left","white-space":"nowrap","text-align":"center"}).addClass("NFI-button NFI"+f).html(c.label);a(b).after(g),a(b).wrap(h),a(".NFI"+f).wrapAll('<div class="NFI-wrapper" id="NFI-wrapper-'+f+'" />'),a(".NFI-wrapper").css({overflow:"auto",display:"inline-block"}),a("#NFI-wrapper-"+f).addClass(a(b).attr("class")),a(b).css({opacity:0,position:"absolute",border:"none",margin:0,padding:0,top:0,right:0,cursor:"pointer",height:"60px"}).addClass("NFI-current"),a(b).on("change",function(){var d=a(b).val();if(c.fullPath)g.val(d);else{var e=d.split(/[/\\]/);g.val(e[e.length-1])}}),a(b).attr("data-styled",!0)}})}}(jQuery);