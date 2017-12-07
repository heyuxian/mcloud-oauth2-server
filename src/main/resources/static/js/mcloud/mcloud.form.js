;$(function ($) {
    var MValidate = function (form, options) {
        var defaults = {
            rules: {},
            submitHandler: null,
            highlight: function (input) {
                $(input).parents('.form-line').addClass('error');
            },
            unhighlight: function (input) {
                $(input).parents('.form-line').removeClass('error');
            },
            errorPlacement: function (error, element) {
                $(element).parents('.input-group').append(error);
                $(element).parents('.form-group').append(error);
            }
        };
        var settings = $.extend({}, defaults, options);
        return form.validate({
            rules: settings.rules,
            submitHandler: settings.submitHandler,
            highlight: settings.highlight,
            unhighlight: settings.unhighlight,
            errorPlacement: settings.errorPlacement
        });
    };
    $.fn.mvalidate = function (options) {
        return MValidate($(this), options);
    };
});
