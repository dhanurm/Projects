
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();


  var $inputItem = $(".js-inputWrapper");
$inputItem.length && $inputItem.each(function() {
    var $this = $(this),
        $input = $this.find(".formRow--input"),
        placeholderTxt = $input.attr("placeholder"),
        $placeholder;

    $input.after('<span class="placeholder">' + placeholderTxt + "</span>"),
    $input.attr("placeholder", ""),
    $placeholder = $this.find(".placeholder"),

    $input.val().length ? $this.addClass("active") : $this.removeClass("active"),

    $input.on("focusout", function() {
        $input.val().length ? $this.addClass("active") : $this.removeClass("active");
    }).on("focus", function() {
        $this.addClass("active");
    });
});
