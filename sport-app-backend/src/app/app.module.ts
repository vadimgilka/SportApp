import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthModule } from 'src/auth/auth.module';
import { ExercisesModule } from 'src/exercises/exercises.module';

@Module({
  imports: [AuthModule, ExercisesModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
