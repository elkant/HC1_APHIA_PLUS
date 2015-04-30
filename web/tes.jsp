<%-- 
    Document   : tes
    Created on : Feb 18, 2015, 9:40:28 AM
    Author     : MANUEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
      <link href="css/multiselect.css" media="screen" rel="stylesheet" type="text/css">
      <link href="css/bootstrap.css" media="screen" rel="stylesheet" type="text/css">
      <link href="css/theme.css" media="screen" rel="stylesheet" type="text/css">
      <link href="css/application.css" media="screen" rel="stylesheet" type="text/css">
     
 
     
  </head>
  <body>
      
    <select class="searchable" id="searchable" multiple='multiple' name="dte[]" >
      <option value='elem_1'>elem 1</option>
      <option value='elem_2'>elem 2</option>
      <option value='elem_3'>we</option>
      <option value='elem_4'>elem 4</option>
   
      <option value='elem_100'>man</option>
    </select>
    
      <script src="js/jquery.js" type="text/javascript"></script>
  
    <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/jquery.tinysort.js" type="text/javascript"></script>
    <script src="js/jquery.quicksearch.js" type="text/javascript"></script>
    <script src="js/jquery.multi-select.js" type="text/javascript"></script>
    <script src="js/rainbow.js" type="text/javascript"></script>
    <script src="js/application.js" type="text/javascript"></script>
  

       <script>
       
         $('.searchable').multiSelect({
  selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='try \"12\"'>",
  selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='try \"4\"'>",
  afterInit: function(ms){
    var that = this,
        $selectableSearch = that.$selectableUl.prev(),
        $selectionSearch = that.$selectionUl.prev(),
        selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
        selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
    .on('keydown', function(e){
      if (e.which === 40){
        that.$selectableUl.focus();
        return false;
      }
    });

    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
    .on('keydown', function(e){
      if (e.which == 40){
        that.$selectionUl.focus();
        return false;
      }
    });
  },
  afterSelect: function(){
    this.qs1.cache();
    this.qs2.cache();
  },
  afterDeselect: function(){
    this.qs1.cache();
    this.qs2.cache();
  }
});
      </script>
  </body>
</html>
