import { Injectable } from '@nestjs/common';
 import * as admin from 'firebase-admin';
import { PayloadFCM } from './payload';
import logger from 'src/prisma/logger';

 admin.initializeApp({
   credential: admin.credential.cert(process.env.FCM_PRIVATE_JSON),
 });
 @Injectable()
 export class FcmNotificationService {
   constructor() {
    logger.info(admin)
   };

   async sendMessage(payload : PayloadFCM){
      try {
        logger.info(JSON.stringify(payload))
         await admin.messaging().send(payload);
      }
      catch(e){
        logger.error(payload + " not was messaged because " + e.message);
      }
   }
 }