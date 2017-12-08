;$(function ($) {
    MCloud.rest = {
        get: function (options) {
            var settings = {
                url: options.url,
                method: 'get',
                data: options.data,
                success: options.success,
                error: options.error
            };
            MCloud.rest.ajax(settings);
        },
        post: function (options) {
            var settings = {
                url: options.url,
                method: 'post',
                data: options.data,
                success: options.success,
                error: options.error
            };
            MCloud.rest.ajax(settings);
        },
        patch: function (options) {
            var settings = {
                url: options.url,
                method: 'patch',
                data: options.data,
                success: options.success,
                error: options.error
            };
            MCloud.rest.ajax(settings);
        },
        delete: function (options) {
            var settings = {
                url: options.url,
                method: 'delete',
                data: options.data,
                success: options.success,
                error: options.error
            };
            MCloud.rest.ajax(settings);
        },
        ajax: function (options) {
            var defaults = {
                url: null,
                contentType: 'application/json',
                method: 'get',
                data: null,
                success: null,
                error: function (e) {
                    console.log(e);
                    if (e.responseJSON) {
                        swal(e.responseJSON.error + ': '
                            + e.responseJSON.message);
                    } else {
                        swal(e.responseText);
                    }
                }
            };
            var settings = $.extend({}, defaults, options);
            if (!settings.url.startsWith(MCloud.context)) {
                if (settings.url.startsWith('/')) {
                    var context = MCloud.context.substring(
                        0, MCloud.context.length - 1);
                    settings.url = context + settings.url;
                } else {
                    settings.url = MCloud.context + settings.url;
                }
            }
            $.ajax(settings);
        }
    }
});
