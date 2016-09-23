$(document).ready(function() {
    
    //DIALOG Add.
    $('#dialog').dialog({autoOpen: false});
    
    $('#add').click(function(){
        $("#dialog").dialog('open');
    });
    
    $('#ajouter').click(function(){
        $('#dialog').dialog('close');
        $('#addImage').val('');
        $('#addFr').val('');
        $('#addEn').val('');
        $('#addRo').val('');
        $('#addLt').val('');
        $('#addFamille').val('');
        $('#addGroupe').val('');
        $('#addLiens').val('');
    });
    //DIALOG Remove.
    $('#del').dialog({autoOpen: false});
    
    $('#delete').click(function(){
        $("#del").dialog('open');
    });
    
    $('#sup').click(function(){
        $('#del').dialog('close');
        $('#deleteName').val('');
    });
    
    //DIALOG Modify.
    $('#mod').dialog({autoOpen: false});
    
    $('#modify').click(function(){
        $("#mod").dialog('open');
    });
    
    $('#ok').click(function(){
        $('#mod').dialog('close');
        $('#modifyName').val('');
        $("#modifyingDialog").dialog('open');
    });
    //Dialog to modify data
    $('#modifyingDialog').dialog({autoOpen: false});
    
    $('#modifyData').click(function(){
        $('#modifyingDialog').dialog('close');
   
    });
});
