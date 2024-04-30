import { IsNotEmpty, IsString } from 'class-validator';

export class CreateBiologicalAdditiveDTO {
  author_id?: number;

  @IsString()
  @IsNotEmpty()
  name: string;

  @IsString()
  @IsNotEmpty()
  description: string;
}

export class UpdateBiologicalAdditiveDTO {
  id: number;
  author_id?: number;

  @IsString()
  @IsNotEmpty()
  name: string;

  @IsString()
  @IsNotEmpty()
  description: string;
}
