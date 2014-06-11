/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when upgrading.
*/
Ext.Loader.setConfig({
    enabled : true,
    paths : {
        'HMS' : '/hms/staticResources/packages/wardenHome/app'
    }
});

Ext.create('Ext.data.Store', {
    id:'PA-id',
    fields:['outpassId', 'enrollNo', 'reason', 'fromDate', 'toDate', 'place'],
	proxy : {
		type : 'ajax',
		url : 'getOutpass',
		reader : {
			type : 'json',
			totalProperty : 'totalCount',
			root : 'outpass',
			successProperty : 'success'
		},
	}
});

Ext.create('Ext.data.Store', {
    id:'WA-id',
    fields:['outpassId', 'enrollNo', 'reason', 'fromDate', 'toDate', 'place'],
	proxy : {
		type : 'ajax',
		url : 'getOutpass',
		reader : {
			type : 'json',
			totalProperty : 'totalCount',
			root : 'outpass',
			successProperty : 'success'
		},
	}
});

Ext.create('Ext.data.Store', {
    id:'NEW-id',
    fields:['outpassId', 'enrollNo', 'reason', 'fromDate', 'toDate', 'place'],
	proxy : {
		type : 'ajax',
		url : 'getOutpass',
		reader : {
			type : 'json',
			totalProperty : 'totalCount',
			root : 'outpass',
			successProperty : 'success'
		},
	}
});

Ext.application({
	name: 'HMS',
    appFolder : '/hms/staticResources/packages/wardenHome/app',
    autoCreateView : false,
    launch : function() {
    Ext.widget({
		xtype : 'container',
		renderTo : Ext.getBody(),
		margin : '0 130 0 130',
		height : '100%',
		items : [ {
			xtype : 'panel',
			title : 'Automated Hostel Outpass Management System',
			width : '100%',
			height : '100%',
			items : [{
				xtype : 'grid',
				title: 'Pending your approval',
				margin : 20,
				padding : 30,
				maxHeight : 450,
			    store: Ext.data.StoreManager.lookup('PA-id'),
			    listeners :{ 
					render : function(grid, col, opts){
						grid.getStore().load({
							params : {
								outpassStatus : 'PA'
							}
						});
					}
				},
			    columns: [{
						text : 'Outpass Id',
						dataIndex : 'outpassId'
					}, {
						text : 'Enrollment Number',
						dataIndex : 'enrollNo'
					}, {
						text : 'Reason',
						dataIndex : 'reason',
						flex : 1
					}, {
						text : 'From Date',
						dataIndex : 'fromDate'
					}, {
						text : 'To Date',
						dataIndex : 'toDate'
					}, {
						text : 'Place of Travel',
						dataIndex : 'place',
						flex : 1
					}, {
						xtype:'actioncolumn',
			            width:50,
			            items: [{
			                icon: '/hms/staticResources/resources/images/approve.png',  // Use a URL in the icon config
			                tooltip: 'Approve',
			                handler: function(grid, rowIndex, colIndex) {
			                    var rec = grid.getStore().getAt(rowIndex);
			                    alert("Edit " + rec.get('enrollNo'));
			                }
			            },{
			                icon: '/hms/staticResources/resources/images/reject.png',
			                tooltip: 'Reject',
			                handler: function(grid, rowIndex, colIndex) {
			                    var rec = grid.getStore().getAt(rowIndex);
			                    alert("Terminate " + rec.get('enrollNo'));
			                }
			            }]
					}
			    ]
			}, {
				xtype : 'grid',
				title: 'New Outpass',
				margin : 20,
				padding : 30,
				maxHeight : 450,
			    store: Ext.data.StoreManager.lookup('NEW-id'),
			    listeners :{ 
					render : function(grid, col, opts){
						grid.getStore().load({
							params : {
								outpassStatus : 'NEW'
							}
						});
					}
				},
			    columns: [{
						text : 'Outpass Id',
						dataIndex : 'outpassId'
					}, {
						text : 'Enrollment Number',
						dataIndex : 'enrollNo'
					}, {
						text : 'Reason',
						dataIndex : 'reason',
						flex : 1
					}, {
						text : 'From Date',
						dataIndex : 'fromDate'
					}, {
						text : 'To Date',
						dataIndex : 'toDate'
					}, {
						text : 'Place of Travel',
						dataIndex : 'place',
						flex : 1
					}
			    ]
			
			}, {
				xtype : 'grid',
				title: 'History of Outpasses',
				margin : 20,
				padding : 30,
				maxHeight : 450,
			    store: Ext.data.StoreManager.lookup('WA-id'),
			    listeners :{ 
					render : function(grid, col, opts){
						grid.getStore().load({
							params : {
								outpassStatus : 'WA'
							}
						});
					}
				},
			    columns: [{
						text : 'Outpass Id',
						dataIndex : 'outpassId'
					}, {
						text : 'Enrollment Number',
						dataIndex : 'enrollNo'
					}, {
						text : 'Reason',
						dataIndex : 'reason',
						flex : 1
					}, {
						text : 'From Date',
						dataIndex : 'fromDate'
					}, {
						text : 'To Date',
						dataIndex : 'toDate'
					}, {
						text : 'Place of Travel',
						dataIndex : 'place',
						flex : 1
					}
			    ]
			}]			
		}]
    });
    }
});
