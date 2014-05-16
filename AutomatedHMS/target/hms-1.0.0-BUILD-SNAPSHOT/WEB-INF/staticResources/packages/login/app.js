/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when upgrading.
*/
Ext.Loader.setConfig({
    enabled : true,
    paths : {
        'HMS' : '/hms/staticResources/packages/login/app'
    }
});

Ext.application({
	name: 'HMS',
    appFolder : '/hms/staticResources/packages/login/app',
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
				columnWidth : .65,
				xtype : 'panel',
				padding : '40 40 40 40',
				height : 590,
				items : [{
					xtype : 'image',
					src: '/hms/staticResources/resources/images/amityHostel.jpg',
				    autoEl: 'div',
				    renderTo: Ext.getBody()
				}]
				
			},{
				columnWidth : .3,
				xtype : 'panel',
				title : 'Enter Your Login Details',
				padding : '120 20 40 20',
				height : 400,
				items : [{
					xtype : 'form',
					url: 'validate.login',
					margin : 5,
					border : 0,
					padding : 10,
					layout: 'anchor',
				    defaults: {
				        anchor: '100%',
				        labelWidth : 130
				    },
				    // The fields
				    defaultType: 'textfield',
				    items: [{
				        fieldLabel: 'Enrollment Number',
				        name: 'name',
				        allowBlank: false
				    },{
				    	inputType: 'password',
				        fieldLabel: 'Password',
				        name: 'pass',
				        allowBlank: false
				    }],
				    buttons: [{
				        text: 'Reset',
				        handler: function() {
				            this.up('form').getForm().reset();
				        }
				    }, {
				        text: 'Login',
				        formBind: true,
				        disabled: true,
				        handler: function() {
				            var form = this.up('form').getForm();
				            if (form.isValid()) {
				                form.submit({
				                    success: function(form, action) {
				                       Ext.Msg.alert('Success', action.result.msg);
				                    },
				                    failure: function(form, action) {
				                        Ext.Msg.alert('Failed', action.result.msg);
				                    }
				                });
				            }
				        }
				    }],
					
				}, {
					xtype : 'box',
					margin : '10 20 0 20',
					html : 	'<div>'+
							'Not a member?<br/><br/>'+
							'<a href="/hms/register">Register</a>'+
							'</div>'
				}]
				
			}]
		}]
    });
    }
});
