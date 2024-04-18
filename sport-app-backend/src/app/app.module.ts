import { ServeStaticModule } from '@nestjs/serve-static';
import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthModule } from 'src/auth/auth.module';
import { ExercisesModule } from 'src/exercises/exercises.module';
import { MulterModule } from '@nestjs/platform-express';
import * as path from 'path';
import { TrainsModule } from 'src/trains/trains.module';
import { HistoryModule } from 'src/history/history.module';


@Module({
  imports: [AuthModule, ExercisesModule, TrainsModule, HistoryModule, MulterModule.register({
    dest: './uploads', 
  }),
  ServeStaticModule.forRoot({
    rootPath: path.resolve('./uploads'), 
    serveRoot: '/uploads', 
  }),],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
