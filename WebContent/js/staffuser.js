function newCar(){
	$('#car-dlg').dialog('open').dialog('setTitle','录入车辆信息');
	$('#car-fm').form('clear');
	url = 'NewCar';
}

function saveUser(){
	$('#car-fm').form('submit',{
		url: url,
//		onSubmit: function(){
//			return $(this).form('validate');
//		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.status==0){
				$.messager.show({
					title: 'Error',
					msg: result.msg
				});
				$('#car-dlg').dialog('close');
			} else {
				$.messager.show({
					title: 'Success',
					msg: result.msg
				});
				$('#car-dlg').dialog('close');		// close the dialog
				$('#car-dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function editCar(){
	var row = $('#car-dg').datagrid('getSelected');
	if (row){
		$('#car-dlg').dialog('open').dialog('setTitle','修改车辆信息');
		$('#car-fm').form('load',row);
		$('#car-fm #carid-input').textbox({
			readonly:true,
		})
		url = 'UpdateCar?id='+row.id;
	}else{
		alert("请选择要修改的车");
	}
}

function destroyCar(){
	var row = $('#car-dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','确定删除这辆车?',function(r){
			if (r){
				$.post('DestoryCar',{carid:row.carid},function(result){
					if (result.status==1){
						$('#car-dg').datagrid('reload');	// reload the user data
						$.messager.show({	// show error message
							title: 'Success',
							msg: result.msg
						});
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.msg
						});
					}
				},'json');
			}
		});
	}
}

$(function(){
	
	$("#lease-dg").datagrid({
		data:[
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
			{clientid:'u003',carid:'c001',name:'帕萨特',carnumber:'京A11111',startdate:'2018-5-9',rentdays:'20',method:'天',returndate:'2018-5-28',state:'超期',deposit:'500',price:'15000'},
		],
		onClickRow:function(rowIndex, rowData){
			
//			$('#rent-dlg').dialog('open').dialog('setTitle','还车');
//			$('#rent-fm').form('load',rowData);
//			//url = 'update_user.php?id='+rowData.carid;
//			var row = $("#rent").datagrid('getSelected');
//			
//			$("#rent-price").val(row.dayrent);

			$.messager.confirm('确认','确定还车？',function(r){
				if (r){
					$.post('ReturnCar',{
						clientid:rowData.clientid,
						carid:rowData.carid
					},function(resp){
						if(resp.status==1){
							messager.alert("提示",resp.msg,"msg");
							$("#lease-dg").datagrid('reload');
						}else{
							messager.alert("提示",resp.msg,"msg");
						}
					})
				}else{
					return false;
				}
			});
		}
	})
})