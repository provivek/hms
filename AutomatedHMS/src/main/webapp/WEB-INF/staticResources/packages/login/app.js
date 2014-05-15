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
            xtype : 'panel',
            renderTo : Ext.getBody(),
            items : [{
                xtype : 'panel',
                title : 'TEST',
                width : 100,
                height : 100
            }]
        });
    }
});
