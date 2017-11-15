;$(function ($) {

  var DataTable = function ($this, opt) {
    var columnDefOpt = [{
      searchable: false,
      orderable: false,
      className: 'select-checkbox',
      targets: 0
    }];
    if (opt.columnDefs) {
      columnDefOpt = columnDefOpt.concat(opt.columnDefs);
    }
    if (opt.enableEdit) {
      var editOpt = [{
        orderable: false,
        targets: -1,
        render: function (data) {
          return '<a href="user.html"><i class="fa fa-pencil"></i></a>  ' +
              '<a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>';
        }
      }];
      columnDefOpt = columnDefOpt.concat(editOpt);
    }
    this.defaults = {
      dom: '<"top"<"clear">>rt<"bottom"i<"page-info"l>p<"clear">>',
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
      bAutoWidth: false,
      columns: [],
      bServerSide: true,
      bInfo: false,
      sAjaxSource: null,
      fnServerData: null,
      fnServerParams: null
    };
    var options = $.extend({}, this.defaults, opt);
    var table = $($this).DataTable({
      dom: options.dom,
      language: options.language,
      columnDefs: columnDefOpt,
      paginate: true,
      searching: false,
      ordering: false,
      select: options.select,
      columns: options.columns,
      bAutoWidth: options.bAutoWidth,
      sScrollX: true,
      bServerSide: options.bServerSide,
      fnServerParams: options.fnServerParams,
      bInfo: options.bInfo,
      aLengthMenu: [10, 20, 30],
      sAjaxSource: options.sAjaxSource,
      ajax: {
        dataSrc: "content"
      },
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
        if (options.fnServerData) {
          options.fnServerData(def);
        }
        $.ajax({
          url: sSource,
          data: def,
          dataType: "json",
          success: function (result) {
            fnCallback(result);
          }
        });
      },
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
  $.fn.datatable = function (opt) {
    return DataTable($(this), opt);
  };

});
