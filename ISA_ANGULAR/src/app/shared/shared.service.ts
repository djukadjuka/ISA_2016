import { Injectable } from '@angular/core';

var Stomp = require('stompjs');
var SockJS = require('sockjs-client');

@Injectable()
export class SharedService {

  //Data about currently logged user
  public isAdmin : boolean = true;
  public isChef : boolean = false;
  public isCustomer : boolean = true;
  public isBartender : boolean = false;
  public isWaiter : boolean = false;
  public isSocialAccount : boolean = true;
  public isManager : boolean = true;
  public isDeliverer : boolean = true;

  public userId : String = "1";
  public userEmail : String = "";

  constructor() { 
    

  }


}

/*
<!--============================-->
<!--======SOCKET SCRIPTS========-->
<!--============================-->
  <script type="text/javascript">
      var stompClient = null;


      function connect(){
        if(stompClient != null)
          return;
        var socket = new SockJS('http://localhost:4400/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({},function(frame){
          
          //all subscriptions
          stompClient.subscribe('/message/recieveMessage/1',function(messageOutput){
            showMessageOutput1(JSON.parse(messageOutput.body));
          });
          stompClient.subscribe('/message/recieveMessage/2',function(messageOutput){
            showMessageOutput2(JSON.parse(messageOutput.body));
          });
        });
      }

      function sendMessage(){
        var from = document.getElementById('from').value;
        var text = document.getElementById('text').value;
        stompClient.send("/app/chat",{},JSON.stringify({'from':from,'text':text}));
      }

      function sendMessageNumber(number){
        var from = document.getElementById('from').value;
        var text = document.getElementById('text').value;
        stompClient.send("/app/sendMessageHere/"+number,{},JSON.stringify({'from':from,'text':text}));
      }

      function showMessageOutput1(messageOutput){
        document.getElementById('messages_1').innerHTML = messageOutput
      }
      function showMessageOutput2(messageOutput){
        document.getElementById('messages_2').innerHTML = messageOutput;
      }

      //disconnect stomp client...
      function disconnect(){
        if(stompClient != null){
          stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected stomp client");
      }

  </script>
<!--============================-->
<!--=====END SOCKET SCRIPTS=====-->
<!--============================-->
*/