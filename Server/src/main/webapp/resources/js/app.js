(function () {

    // 排序
    $('body').on('click', 'a[data-toggle="sort"]', function (e) {
        e.preventDefault();
        var $this = $(this);
        var sort = $this.data('sort');
        var url = $this.data('url');
        if (url.indexOf('?') > 0) {  // 包含?
            location.href = url + '&sort=' + sort;
        } else {
            location.href = url + '?sort=' + sort;
        }
    });
    // 删除
    $('body').on('click', 'a[data-toggle="delete"]', function (e) {
        e.preventDefault();
        var $this = $(this);
        var confirm_message = $this.data('confirm') ? $this.data('confirm') : '确定要删除记录吗？';

        $.fn.confirm({
            title: '确认',
            text: '确认要删除记录吗？',
            function: function () {
                var url = $this.attr('href');
                $.get(url, function (data) {
                    if (data == 'success') {
                        $.fn.notify({
                            type: 'success',
                            text: '操作成功！',
                            callback: function () {
                                location.reload();
                            },
                            delay: 1000
                        });
                    } else {
                        $.fn.notify({type: 'error', text: data, delay: 1000})
                    }
                });
            }
        });
    });

    // 禁止modal缓存
    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });

    // 全选
    $(document).on('click.check.data-api', '[data-toggle="check"]', function (e) {
        var $this = $(this);
        var $target = $($this.data('target'));
        $target.each(function (i, e) {
            e.checked = $this.get(0).checked;
        });
    });

    // 鼠标移入，显示
    $(document).on('mouseover', '[data-toggle="mouse-hover"]', function () {
        var $this = $(this);
        $($this.data('target')).removeClass('hide').show();
    });

    // 鼠标移出，隐藏
    $(document).on('mouseleave', '[data-toggle="mouse-hover"]', function () {
        var $this = $(this);
        $($this.data('target')).hide();
    });

    // 获得全选的id
    window.DomUtils = {

        defaults: {
            check: {
                selector: 'input.check'
            }
        },
        //获取页面中选中的多选按钮的值
        getCheckedValues: function (options) {
            var config = $.extend(this.defaults.check, options),
                values = '';
            $(config.selector + ':checked').each(function (i, item) {
                values += ',' + $(item).val();
            });
            return values.replace(',', '');
        }
    };

    /**
     * 对项目的方法进行封装
     * @type {{SubmitForm}}
     */
    window.App = {

        SubmitForm: function (options) {
            $(options.selector).ajaxForm({
                success: function (data) {
                    $('#adminModalLg').modal('hide');
                    if (data == 'success') {
                        $.fn.notify({
                            type: 'success',
                            text: '操作成功！',
                            callback: function () {
                                location.reload();
                            },
                            delay: 50
                        });
                    } else {
                        $.fn.notify({type: 'error', text: data, delay: 3000})
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            });
        }
    }

    /**
     * 分页封装
     * @param options
     */
    $.fn.pagination = function (options) {
        var totalPage = options.totalPage;      // 总页数
        var pageNumber = options.pageNumber;    // 页号
        var $pagination = options.pagination;   // 分页的元素，一般为ul
        var path = location.href;
        if (path[0] == '?')
            path = path.substr(1, path.length - 1);
        var paths = path.split('&');
        for (var i = 0; i < paths.length; i++) {
            if (paths[i].indexOf('page=') > -1) {
                paths.splice(i, 1);
            }
        }

        path = paths.join("&");
        if (path.indexOf('?') > -1) {
            path += '&page=';
        } else {
            path += '?page=';
        }
        if (totalPage > 1) {
            $pagination.append('<li><a href=' + path + '1>首页</a></li>')

            for (var i = 1; i <= totalPage; i++) {
                if (i == pageNumber) {
                    $pagination.append('<li class="active"><a href="#">' + i + '</a></li>');
                } else {
                    $pagination.append('<li><a href="' + (path + i) + '">' + i + '</a></li>');
                }
            }
            $pagination.append('<li><a href="' + path + totalPage + '">尾页</a></li>');
        }
    };

    /**
     * 提示插件
     * @param option
     */
    $.fn.notify = function (option) {
        var type = option.type;
        var text = option.text;
        var callback = option.callback;
        var delay = option.delay;
        new PNotify({
            text: text,
            styling: 'bootstrap3',
            type: type,
            width: '300px',
            delay: delay,
            nonblock: {
                nonblock: false
            },
            before_open: function (pnotify) {
                pnotify.elem.css({
                    'top': "30px",
                    'left': $(window).width() / 2 - (pnotify.elem.width() / 2)
                });
            },
            after_close: callback
        });
    };

    $.fn.confirm = function (option) {
        var title = option.title;
        var text = option.text;
        var okfunction = option.function;
        new PNotify({
            title: title,
            text: text,
            icon: 'glyphicon glyphicon-question-sign',
            hide: false,
            confirm: {
                confirm: true,
                buttons: [{
                    text: '确认',
                    addClass: 'btn-primary',
                    click: function (notice) {
                        okfunction();
                        notice.remove();
                    }
                }, {
                    text: '取消',
                    addClass: 'btn-default',
                    click: function (notice) {
                        notice.remove();
                    }
                }]
            },
            buttons: {
                closer: false,
                sticker: false
            },
            history: {
                history: false
            }, before_open: function (pnotify) {
                pnotify.elem.css({
                    'top': "30px",
                    'left': $(window).width() / 2 - (pnotify.elem.width() / 2)
                });
            }
        });
    };
    $.fn.toggleSide = {
        v: 0,
        fun: {
            hideSide: function () {
                $('#sideIcon').removeClass('fa fa-angle-double-left').addClass('fa fa-angle-double-right');
                $('div.sidebar a>span.stitle').hide();
                $('.sidebar').css('width', 70);
                $('#page-wrapper').animate({'margin-left': '70px'}, 300);
                $.fn.toggleSide.v = 1;
            },
            showSide: function () {
                $('#sideIcon').removeClass('fa fa-angle-double-right').addClass('fa fa-angle-double-left');
                $('#page-wrapper').animate({'margin-left': '200px'}, 300, function () {
                    $('.sidebar').css('width', 200);
                    $('div.sidebar a>span.stitle').show();
                });
                $.fn.toggleSide.v = 0;
            }
        },
        toggle: function () {
            if ($.fn.toggleSide.v == 0) { // 使关闭
                $.fn.toggleSide.fun.hideSide();
            } else {      // 使敞开
                $.fn.toggleSide.fun.showSide();
            }
        }
    };


})(jQuery);