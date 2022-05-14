package com.example.subscriberlisteasy.uisubscriberlist.interfaces

import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity

interface IOnClick {
    fun onClick(click: SubscriberEntity?)
}
