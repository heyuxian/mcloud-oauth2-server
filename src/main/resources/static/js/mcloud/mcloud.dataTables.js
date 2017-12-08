;$(function ($) {
    var MDataTable = function ($this, options) {
        var columnDefOpt = [{
            searchable: false,
            orderable: false,
            className: 'select-checkbox',
            targets: 0,
            render: function (data) {
                return data;
            }
        }];
        if (options.columnDefs) {
            columnDefOpt = columnDefOpt.concat(options.columnDefs);
        }
        this.defaults = {
            dom: 'Bfrtip',
            language: {
                'emptyTable': '没有数据',
                'loadingRecords': '加载中...',
                'processing': '查询中...',
                'search': '搜索:',
                'lengthMenu': '每页 _MENU_ 条记录',
                'zeroRecords': '没有数据',
                'paginate': {
                    'next': '下一页',
                    'previous': '上一页'
                },
                'info': '第 _PAGE_ 页 / 共 _PAGES_ 页',
                'infoEmpty': '没有数据',
                'infoFiltered': '(从 _MAX_ 条记录中筛选)'
            },
            select: {
                style: 'multi',
                selector: 'td:first-child'
            },
            buttons: [],
            bAutoWidth: false,
            columns: [],
            bServerSide: true,
            bInfo: false,
            url: null,
            fnServerData: null,
            fnServerParams: null
        };
        var settings = $.extend({}, this.defaults, options);
        var table = $($this).DataTable({
            dom: settings.dom,
            language: settings.language,
            columnDefs: columnDefOpt,
            paginate: true,
            buttons: settings.buttons,
            searching: false,
            ordering: false,
            select: settings.select,
            columns: settings.columns,
            bAutoWidth: settings.bAutoWidth,
            sScrollX: true,
            bServerSide: settings.bServerSide,
            fnServerParams: settings.fnServerParams,
            bInfo: settings.bInfo,
            aLengthMenu: [10, 20, 30],
            sAjaxSource: settings.sAjaxSource,
            fnServerData: function (sSource, aoData, fnCallback) {
                var pageNum = 1;
                var pageSize = 10;
                var sEcho = 1;
                for (var i = 0; i < aoData.length; i++) {
                    if ("sEcho" === aoData[i].name) {
                        sEcho = aoData[i].value;
                    }
                    if ("iDisplayStart" === aoData[i].name) {
                        pageNum = aoData[i].value;
                    }
                    if ("iDisplayLength" === aoData[i].name) {
                        pageSize = aoData[i].value;
                    }
                }
                pageNum = pageNum / pageSize + 1;
                var def = {
                    page: pageNum,
                    size: pageSize,
                    sEcho: sEcho
                };
                if (settings.fnServerData) {
                    settings.fnServerData(def);
                }
                MCloud.rest.get({
                    url: sSource,
                    data: def,
                    success: function (result) {
                        var data = {
                            iTotalRecords: result.totalElements,
                            iTotalDisplayRecords: result.totalElements,
                            data: result.content
                        };
                        fnCallback(data);
                    }
                });
            }
        });
        var selected = false;
        if ($("#selectAll").prop("checked")) {
            $("#selectAll").prop("checked", false);
        }
        $("#selectAll").bind("click", function () {
            if (selected) {
                table.rows().deselect();
                selected = false;
            } else {
                table.rows().select();
                selected = true;
            }
            //var rows = table.rows( { selected: true } );

            //table.draw(false);
            //console.log(rows.data()[0].username);
        });
        return table;
    };
    $.fn.mdatatable = function (options) {
        return MDataTable($(this), options);
    };

});
