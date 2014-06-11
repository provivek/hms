/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when upgrading.
*/
Ext.Loader.setConfig({
    enabled : true,
    paths : {
        'HMS' : '/hms/staticResources/packages/studentHome/app'
    }
});

Ext.create('Ext.data.Store', {
    id:'userDetailsStore',
    fields:['firstName', 'enrollNo', 'lastName'],
    autoLoad : true,
	proxy : {
		type : 'ajax',
		url : 'getUserDetails',
		reader : {
			type : 'json',
			totalProperty : 'totalCount',
			root : 'user',
			successProperty : 'success'
		},
	}
});

Ext.create('Ext.data.Store', {
    id:'my-outpass-store-id',
    fields:['outpassId', 'enrollNo', 'reason', 'fromDate', 'toDate', 'place', 'outpassStatus'],
	proxy : {
		type : 'ajax',
		url : 'getMyOutpass',
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
    appFolder : '/hms/staticResources/packages/studentHome/app',
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
			layout : 'column',
			items : [{
				xtype : 'panel',
				title : 'Apply for Outpass',
				margin : 30,
				padding : 20,
				columnWidth : .65,
				items : [{
					xtype : 'form',
					url: 'apply.outpass',
					margin : 5,
					border : 0,
					padding : 10,
					layout: 'anchor',
				    defaults: {
				        anchor: '100%',
				        labelWidth : 130
				    },
				    // The fields
				    items: [{
				    	xtype : 'textfield',
				        fieldLabel : 'Reason',
				        name: 'reason',
				        allowBlank: false
				    },{
				    	xtype : 'datefield',
				        fieldLabel: 'From Date',
				        name: 'fromDate',
				        allowBlank: false
				    },{
				    	xtype : 'datefield',
				        fieldLabel: 'To Date',
				        name: 'toDate',
				        allowBlank: false
				    },{
				    	xtype : 'textarea',
				        fieldLabel: 'Place of Travel',
				        name: 'place',
				        height : 200,
				        allowBlank: false
				    }],
				    buttons: [{
				        text: 'Reset',
				        handler: function() {
				            this.up('form').getForm().reset();
				        }
				    }, {
				        text: 'Apply',
				        formBind: true,
				        disabled: true,
				        handler: function() {
				            var form = this.up('form').getForm();
				            if (form.isValid()) {
				                form.submit({
				                    success: function(form, action) {
				                    	Ext.Msg.alert('Success', action.result.message);
				                    	form.reset();
				                    	Ext.getCmp('my-outpass-grid').getStore().load();
				                    },
				                    failure: function(form, action) {
				                    	Ext.Msg.alert('Failed', action.result.message);
				                    }
				                });
				            }
				        }
				    }],
				}]
				
			},{
				xtype : 'panel',
				title : 'Welcome',
				margin : 30,
				padding : 20,
				columnWidth : .3,
				items : [{
					xtype : 'dataview',
					store: Ext.data.StoreManager.lookup('userDetailsStore'),
				    tpl:  new Ext.XTemplate(
				    	    '<tpl for=".">',
				            '<div style="padding: 10px;" class="thumb-wrap">',
				              '<br/><span>{firstName}  {lastName}</span>',
				              '<br/><span>{enrollNo}</span>',
				              '<br/><span><a href="#">logout</a></span>',
				            '</div>',
				        '</tpl>'
				    ),
				    itemSelector: 'div.thumb-wrap',
				    emptyText: 'No images available'
				} , {
					xtype : 'image',
					margin : 10,
					padding : 5,
					src: '/hms/staticResources/resources/images/amitylogo.png',
				    autoEl: 'div',
				    renderTo: Ext.getBody()
				}]
				
			}]
		}, {
			xtype : 'grid',
			title: 'My Outpasses',
			id : 'my-outpass-grid',
			padding : 40,
			maxHeight : 450,
		    store: Ext.data.StoreManager.lookup('my-outpass-store-id'),
		    listeners :{ 
				render : function(grid, col, opts){
					grid.getStore().load();
				}
			},
		    columns: [{
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
				text : 'Outpass Status',
				dataIndex : 'outpassStatus'
			}
		  ]
		}]
    });
    }
});
