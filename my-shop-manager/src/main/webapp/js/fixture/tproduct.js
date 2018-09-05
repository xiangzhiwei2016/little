$(function () {
    $("#jqGrid").jqGrid({
        url: '../tproduct/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '商品名称', name: 'productName', index: 'product_name',align:"center",  width: 80},
			{label: '类别名称', name: 'categoryName', index: 'category_name',align:"center",  width: 80},
			{label: '会员价', name: 'vipPriceStr', index: 'vip_price',align:"center",  width: 80},
			{label: '原价', name: 'priceStr', index: 'price',align:"center",  width: 80},
			/*{label: '单位', name: 'unit', index: 'unit',align:"center",  width: 80},*/
			{label: '是否支持网上购买', name: 'buystatus', index: 'buy_status',align:"center",  width: 80,
				formatter: function (value) {
					var result = "-";
					if("1"== value){
						result = "是";
					}else if("0"== value){
						result = "否";
					}
					return result;
            }

			},
/*			{label: '数量', name: 'countNum', index: 'count_num', width: 80},*/	
		{label: '标签', name: 'tag', index: 'tag', align:"center", width: 80},
		{label: '商品描述', name: 'description', index: 'description',align:"center",  width: 80}
		/*{label: '商品状态', name: 'status', index: 'status',align:"center",  width: 80},*/
		/*{label: '创建时间', name: 'createTime', index: 'create_time',align:"center",  width: 80}/*,
			{label: '修改时间', name: 'modifyTime', index: 'modify_time',align:"center",  width: 80},
			{label: '创建者', name: 'creator', index: 'creator', width: 80},
			{label: '修改者', name: 'modifier', index: 'modifier', width: 80}*/
			],
		viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var categorys = [];
// 单位
var unitTypes = [ {
	"key" : "0",
	"value" : "元"
}, {
	"key" : "1",
	"value" : "元/m"
} , {
	"key" : "2",
	"value" : "元/㎡"
} ];

//是否支持网上购买
var buystatus = [ {
	"key" : "0",
	"value" : "否"
}, {
	"key" : "1",
	"value" : "是"
}  ];

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        visible: false,
		tProduct: {
			imgList:[],
			primaryPicUrl: '',
            listPicUrl: '',
            categoryId: ''
		},
		uploadList:[],
		ruleValidate: {
			productName: [
				{required: true, message: '商品名称不能为空', trigger: 'blur'}
			],
			description: [
					{required: true, message: '商品描述不能为空'}
				],
				
			vipPrice: [
					{required: true, message: '会员价不能为空且只能为数字'}
				],
				price: [
							{required: true, message: '原价不能为空且只能为数字'}
						],
						tag: [
							{required: true, message: '标签不能为空,若有多个用“，”隔开'}
						],
			categoryId: [
						{required: true, message: '商品类别不能为空，请先选择'}
					],
					buystatus: [
							{required: true, message: '是否支持网上购买不能为空，请先选择'}
						],
					unit: [
									{required: true, message: '商品价格单位不能为空，请先选择'}
								]
		},
		q: {
		    name: '',
		    categoryId:''
		},
		categorys : []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.uploadList = [];
			vm.categorys = [];
			vm.tProduct = {};
			vm.getCategory();
		},
		update: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getCategory();
            vm.getInfo(id);
            vm.getImagesGallery(id);
		},
        saveOrUpdate: function (event) {
            let url = vm.tProduct.id == null ? "../tproduct/save" : "../tproduct/update";
            vm.tProduct.imgList = vm.uploadList;
            if(vm.uploadList == null || vm.uploadList.length == 0){
            	alert("至少上传1个商品图片");
        		return;
            }
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tProduct),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
			});
		},
		del: function (event) {
            let ids = getSelectedRows();
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
				    url: "../tproduct/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../tproduct/info/"+id, function (r) {
                vm.tProduct = r.tProduct;
            });
		},
		getImagesGallery: function (id) {//获取图片
            $.get("../tproduct/queryImages?id=" + id, function (r) {
                vm.uploadList = r.list;
                for(var i = 0;i<vm.uploadList.length;i++){
                	vm.uploadList[i].status = "finished";
                }
            });
        },
		getCategory: function () {
            $.get("../tcategory/queryAll", function (r) {
                vm.categorys = r.list;
                categorys = r.list;
            });
        },
        
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name,'categoryId': vm.q.categoryId},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
      //==================上传图片====================//
		 handleView(name) {
           this.imgName = name;
           this.visible = true;
       },
       handleRemove(file) {
           // 从 upload 实例删除数据
           const fileList = this.uploadList;
           this.uploadList.splice(fileList.indexOf(file), 1);
       },
       handleSuccess(res, file) {
           // 因为上传过程为实例，这里模拟添加 url
           file.imgUrl = res.url;
           file.name = res.url;
           vm.uploadList.add(file);
       },
       handleBeforeUpload() {
           const check = this.uploadList.length < 5;
           if (!check) {
               this.$Notice.warning({
                   title: '最多只能上传 5 张图片。'
               });
           }
           return check;
       },
       handleSubmit: function (name) {
           handleSubmitValidate(this, name, function () {
               vm.saveOrUpdate()
           });
       },
       handleFormatError: function (file) {
           this.$Notice.warning({
               title: '文件格式不正确',
               desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
           });
       },
       handleMaxSize: function (file) {
           this.$Notice.warning({
               title: '超出文件大小限制',
               desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
           });
       },
       handleReset: function (name) {
           handleResetForm(this, name);
       },
       handleSuccessPicUrl: function (res, file) {
           vm.tProduct.primaryPicUrl = file.response.url;
       },
       handleSuccessListPicUrl: function (res, file) {
           vm.tProduct.listPicUrl = file.response.url;
       },
       eyeImagePicUrl: function () {
           var url = vm.tProduct.primaryPicUrl;
           eyeImage(url);
       },
       eyeImageListPicUrl: function () {
           var url = vm.tProduct.listPicUrl;
           eyeImage(url);
       },
       eyeImage: function (e) {
           eyeImage($(e.target).attr('src'));
       }
	},
	mounted() {
       this.uploadList = this.$refs.upload.fileList;
       this.getCategory();
   }
});