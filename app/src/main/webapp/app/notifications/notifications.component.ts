import { Component } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';

@Component({
    selector: 'jhi-notificaciones',
    templateUrl: './notifications.component.html',
    styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent {
    private serverUrl = 'http://localhost:8080/socket';
    private title = 'Notifications';
    private stompClient;

    constructor() {
        this.initializeWebSocketConnection();
    }

    initializeWebSocketConnection() {
        const ws = new SockJS(this.serverUrl);
        this.stompClient = Stomp.over(ws);
        const that = this;
        this.stompClient.connect({}, function(frame) {
            that.stompClient.subscribe('/chat', message => {
                if (message.body) {
                    $('.chat').prepend("<div class='alert alert-secondary flex-wrap'>" + message.body + '</div>');
                }
            });
        });
    }

    sendMessage(message) {
        if (message) {
            this.stompClient.send('/app/send/message', {}, message);
        }
        $('#input').val('');
    }
}
