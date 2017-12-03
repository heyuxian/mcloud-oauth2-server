;$(function ($) {
    var MValidate = function ($this, options) {
        this.defaults = {
            rules: {},
            submitHandler: null,
            highlight: function (input) {
                $(input).parents('.form-line').addClass('error');
            },
            unhighlight: function (input) {
                $(input).parents('.form-line').removeClass('error');
            },
            errorPlacement: function (error, element) {
                $(element).parents('.form-group').append(error);
            }
        };
        var settings = $.extend({}, this.defaults, options);
        $this.validate({
            rules: settings.rules,
            submitHandler: settings.submitHandler,
            highlight: settings.highlight,
            unhighlight: settings.unhighlight,
            errorPlacement: settings.errorPlacement
        });
    };
    $.fn.MValidate = function (options) {
        return MValidate($(this), options);
    };
});
