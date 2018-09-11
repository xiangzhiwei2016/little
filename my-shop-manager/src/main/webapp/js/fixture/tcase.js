$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcase/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '案例名称', name: 'caseName', index: 'case_name',  align:"center",width: 80},
			{label: '地址', name: 'address', index: 'address',  align:"center",width: 80},
			{label: '面积', name: 'area', index: 'area', align:"center", width: 80},
			{label: '风格', name: 'style', index: 'style', align:"center", width: 80},
			{label: '采购清单', name: 'products', index: 'products', align:"center",width: 80},
	/*		{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},*/
			{
                label: '案例主图', name: 'imgUrl', index: 'img_url', align:"center", width: 80, formatter: function (value) {
                return transImg(value);
            }
			}],
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

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        visible: false,
        isDisabled: false,
		tCase: {
			imgList:[],
			caseDetailList:[]
		},
		fileList:[],
		caseDetailList:[],
		uploadList:[],
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.isDisabled = false;
			vm.title = "新增";
			vm.tCase = {};
			vm.uploadList = [];
			vm.caseDetailList=[];
			vm.createS();
		},
		update: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.isDisabled = false;
			vm.showList = false;
            vm.title = "修改";
            vm.getImagesGallery(id);
            vm.getInfo(id);
		},
		detail: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.isDisabled = true;
            vm.title = "详情";
            vm.getImagesGallery(id);
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
            let url = vm.tCase.id == null ? "../tcase/save" : "../tcase/update";
            vm.tCase.imgList = vm.uploadList;
            vm.tCase.caseDetailList = vm.caseDetailList;
            console.log("save:"+vm.tCase);
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tCase),
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
				    url: "../tcase/delete",
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
			$.get("../tcase/info/"+id, function (r) {
                vm.tCase = r.tCase;
                console.log(r.tCase);
                // 案例详情
                vm.caseDetailList = r.tCase.caseDetailList;
                console.log(vm.caseDetailList);
            });
		},
		getImagesGallery: function (id) {//获取图片
            $.get("../tcase/queryImages?id=" + id, function (r) {
                vm.uploadList = r.list;
                for(var i = 0;i<vm.uploadList.length;i++){
                	vm.uploadList[i].status = "finished";
                }
            });
        },
        createDefault:function(){
        	return {
    			detailTitle:"",
    			description:"",
    			uploadDetailList:[]
    		};
        },
        createS:function(){
        	var tCaseDetail0 = vm.createDefault();
        	vm.caseDetailList.push(tCaseDetail0);
        },
        del0:function(index){
        	if(vm.caseDetailList.length ==1){
        		alert("至少需要上传一个案例详情");
        		return;
        	}
        	vm.caseDetailList.splice(index, 1);
        },
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
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
          if(fileList.indexOf(file) >= 0){
        	  this.uploadList.splice(fileList.indexOf(file), 1);
          }else{
        	  for(var i=0;i<vm.caseDetailList.length;i++){
        		  var fileList2 = vm.caseDetailList[i].uploadDetailList;
        		  console.log(fileList2);
        		  for(var y=0;y<fileList2.length;y++){
        			  if(fileList2.indexOf(file) >= 0){
        				  fileList2.splice(fileList2.indexOf(file), 1); 
        			  }
        		  }
        	  }
        	  
          }
         
      },
      handleSuccess(res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          vm.uploadList.add(file);
      },
      handleSuccess0(index,res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          vm.caseDetailList[index].uploadDetailList.push(file);
      },
      handleBeforeUpload() {
          const check = this.uploadList.length < 1;
          if (!check) {
              this.$Notice.warning({
                  title: '案例主图，最多只能上传 1 张图片。'
              });
          }
          return check;
      },
      handleBeforeUpload2() {
          const check = this.uploadList.length < 5;
          if (!check) {
              this.$Notice.warning({
                  title: '案例详情图，最多只能上传 5 张图片。'
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
      eyeImageListPicUrl: function () {
          var url = vm.tCase.listPicUrl;
          eyeImage(url);
      },
      eyeImage: function (e) {
          eyeImage($(e.target).attr('src'));
      }
	},
	mounted() {
      this.uploadList = this.$refs.upload.fileList;
  }
});