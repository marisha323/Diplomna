package com.example.Diplomna.factory;

import com.example.Diplomna.enums.NotificationTypes;
import com.example.Diplomna.model.Notification;

import java.util.ArrayList;
import java.util.List;

class NotificationResponseBuilder {

    public List<NotificationResponse> createNotificationResponse(List<Notification> notificationList) {
        List<NotificationResponse> notificationResponseList = new ArrayList<>();
        for (Notification notification : notificationList) {
            notificationResponseList.add(createNotification(notification));
        }
        return notificationResponseList;
    }

    private NotificationResponse createNotification(Notification notification) {
        switch (notification.getNotificationType()) {
            case NotificationTypes.likeNotification:
                return createLikeNotification(notification);

            case NotificationTypes.unsubscribeNotification:
                return createUnsubscribeNotification(notification);

            case NotificationTypes.subscribeNotification:
                return createSubscribeNotification(notification);

            case NotificationTypes.dislikeNotification:
                return createDisLikeNotification(notification);

            case NotificationTypes.commentNotification:
                return createCommentNotification(notification);

            default:
                throw new IllegalStateException("Unexpected value: " + notification.getNotificationType());
        }
    }

    private NotificationResponse createLikeNotification(Notification notification) {
        return new NotificationResponse();
    }

    private NotificationResponse createDisLikeNotification(Notification notification) {
        return new NotificationResponse();
    }

    private NotificationResponse createSubscribeNotification(Notification notification) {
        return new NotificationResponse();
    }

    private NotificationResponse createUnsubscribeNotification(Notification notification) {
        return new NotificationResponse();
    }

    private NotificationResponse createCommentNotification(Notification notification) {
        return new NotificationResponse();
    }

}
