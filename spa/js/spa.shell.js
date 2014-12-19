/*
* spa.shell.js
* Shell modulje for SPA
*/
/*jslint browser : true, continue : true,
devel : true, indent : 2, maxerr : 50,
newcap : true, nomen : true, plusplus : true,
regexp : true, sloppy : true, vars : false,
white : true
*/
/*global $, spa */
spa.shell = (function (){
//---------------- BEGIN MODULE SCOPE VARIABLES --------------
 var configMap = {
  main_html : String()
    + '<div class="spa-shell-head">'
      + '<div class="spa-shell-head-logo">Odu-lion-logo</div>'
      + '<div class="spa-shell-head-acct">account</div>'
      + ' <div class="spa-shell-head-search">search</div>'
    + '</div>'

      + '<div class="spa-shell-main">'
        + '<div class="spa-shell-main-nav"><h1>Hi there</h1>'
		+ '<nav><ol><li>a</li><li>Drop down menus:</li><li>Instructor</li><li>Document</li>'
		+ '<li>Stays grey untill instructor is selected</li></div>'
        + '<div class="spa-shell-main-content">main content</div>'
      + '</div>'

      + '<div class="spa-shell-foot">footer</div>'
      + '<div class="spa-shell-chat">chat</div>'
      + '<div class="spa-shell-modal">f</div>',
	
	chat_extend_time: 1000,
	chat_retract_time: 300,
	chat_extend_height:450,
	chat_retract_height:15,
	
	chat_extended_title : 'Click to REtract',
    chat_retracted_title : 'Click to extend'
	 
},
stateMap = { $container : null,
			is_chat_retracted:true},
jqueryMap = {},

setJqueryMap, toggleChat,onClickChat, initModule;
//----------------- END MODULE SCOPE VARIABLES ---------------
//-------------------- BEGIN UTILITY METHODS -----------------
//--------------------- END UTILITY METHODS ------------------
//--------------------- BEGIN DOM METHODS --------------------
// Begin DOM method----/setJqueryMap/-------------------------
setJqueryMap = function () {
var $container = stateMap.$container;

  jqueryMap = {
   $container : $container,
   $chat : $container.find('.spa-shell-chat')
  };
};
//--------------------/setJqueryMap/--------------------------
//Begin---------------/toggleChat/----------------------------
//Purpose: Extends or retracts slider
//Arguments:
// * do_extend if true, extends slider, if false retracts
// * callback -optional function to execute at end of animation
//Settings:
// * chat_extend_time, chat_retract_time
// * chat_extend_height, chat_retract_height
//Returns: boolean
// * true - slider animation activated
// * false - slider not activated
//
//State :set stateMap.is_chat_retracted
//	*true - slider is retracted 
//	*false - slider is extended
toggleChat = function(do_extend,callback){
 var
  px_chat_ht = jqueryMap.$chat.height(),
  is_open = px_chat_ht === configMap.chat_extend_height,
  is_closed = px_chat_ht === configMap.chat_retract_height,
  is_sliding = !is_open && !is_closed;
  
 //avoid race condition
 if (is_sliding){return false;}
 
 //begin chat slider if false slider is not extended
 if (do_extend) {
  jqueryMap.$chat.animate(
    {height: configMap.chat_extend_height},
	 configMap.chat_extend_time, 
		function () {
			jqueryMap.$chat.attr(
			'title', configMap.chat_extended_title
								);					
stateMap.is_chat_retracted = false;
	   if (callback) {callback(jqueryMap.$chat);}
	  }
  );
    return true;
	
	}
// End extend chat slider
// Begin retract chat slider
    jqueryMap.$chat.animate(
      { height : configMap.chat_retract_height },
        configMap.chat_retract_time,
          function () {
			jqueryMap.$chat.attr('title', configMap.chat_retracted_title
								);
		  stateMap.is_chat_retracted = true;
          
		  if ( callback ){ callback( jqueryMap.$chat ); }
        }
      );
   return true;
};
//--------------end chat slider-----------------------------------



//--------------------- END DOM METHODS ----------------------
//------------------- BEGIN EVENT HANDLERS -------------------
//---------------------Chat toggle----------------------------
onClickChat = function ( event ) {
toggleChat( stateMap.is_chat_retracted );
return false;
};


//-------------------- END EVENT HANDLERS --------------------
//------------------- BEGIN PUBLIC METHODS -------------------
// Begin Public method /initModule/
initModule = function ( $container ) {
stateMap.$container = $container;
$container.html( configMap.main_html );
setJqueryMap();


// initialize chat slider and bind click handler
stateMap.is_chat_retracted = true;
jqueryMap.$chat
	.attr( 'title', configMap.chat_retracted_title )
	.click( onClickChat );

// test toggle
setTimeout( function () {toggleChat( true ); }, 3000 );
setTimeout( function () {toggleChat( false );}, 8000 );
};

// End PUBLIC method /initModule/
return { initModule : initModule };

//------------------- END PUBLIC METHODS ---------------------
}());