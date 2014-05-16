/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when upgrading.
*/
Ext.Loader.setConfig({
    enabled : true,
    paths : {
        'HMS' : '/hms/staticResources/packages/register/app'
    }
});

Ext.application({
	name: 'HMS',
    appFolder : '/hms/staticResources/packages/register/app',
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
				xtype : 'panel',
				padding : '22 40 22 40',
				margin : 5,
				title : 'Register Here !!!',
				items : [{
					xtype : 'form',

					xtype : 'form',
					url: 'reg.user',
					//margin : 5,
					border : 0,
					//padding : 5,
					layout: 'anchor',
				    defaults: {
				        anchor : '60%',
				        labelWidth : 200,
				        padding : 10
				    },
				    defaultType: 'textfield',
				    items: [{
				        fieldLabel: 'Enrollment Number',
				        vtype : 'alphanum',
				        name: 'enrollNo',
				        allowBlank: false
				    },{
				        fieldLabel: 'First Name',
				        name: 'firstName',
				        vtype : 'alpha',
				        allowBlank: false
				    },{
				        fieldLabel: 'Middle Name',
				        vtype : 'alpha',
				        name: 'middleName'
				    },{
				        fieldLabel: 'Last Name',
				        name: 'lastName',
				        vtype : 'alpha',
				        allowBlank: false
				    },{
				        fieldLabel: 'Password',
				        name: 'pass',
				        inputType: 'password',
				        allowBlank: false
				    },{
				        fieldLabel: 'Confirm Password',
				        name: 'confirmPass',
				        inputType: 'password',
				        allowBlank: false
				    },{
				    	xtype : 'datefield',
				        fieldLabel: 'Date Of Birth',
				        name: 'dob',
				        maxValue : new Date(),
				        maxText : 'nothing in the future',
				        allowBlank: false
				    },{
				        fieldLabel: 'Student\'s e-mail',
				        name: 'studentEmail',
				        vtype : 'email',
				        allowBlank: false
				    },{
				        xtype : 'numberfield',
				    	fieldLabel : 'Home Phone Number',
				        name: 'homePhone'				       
				    },{
				    	xtype : 'numberfield',
				        fieldLabel: 'Your Mobile Number',
				        name: 'studentMobile',
				        blankText : 'Enter 10 digit mobile number',
				        allowBlank: false,
				        maxValue : 10,
				        minValue : 10
				    },{
				    	xtype : 'numberfield',
				        fieldLabel: 'Parent Mobile Number',
				        name: 'parentMobile',
				        blankText : 'Enter 10 digit mobile number',
				        maxValue : 10,
				        minValue : 10
				    },{
				        fieldLabel: 'Parent/Gaurdian e-mail ID',
				        name: 'parentEmail',
				        vtype : 'email',
				        allowBlank: false
				    },{
				    	xtype : 'textarea',
				        fieldLabel: 'Home Address',
				        name: 'homeAddress',
				        maxValue : 3000
				    }],
				    buttons: [{
				        text: 'Reset',
				        handler: function() {
				            this.up('form').getForm().reset();
				        }
				    }, {
				        text: 'Register',
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
				    }]
				}]
			}]
		}]
    });
    }
});
