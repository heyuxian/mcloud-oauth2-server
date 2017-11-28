;$(function ($) {
    var MFormValidate = function ($this, options) {
        this.default = {
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

    $.fn.MFormValidate = function (options) {
        return MFormValidate($(this), options);
    };
});
