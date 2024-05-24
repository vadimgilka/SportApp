import { BioType, Prisma } from '@prisma/client';

import { IsEnum, IsNotEmpty, IsOptional, IsString } from 'class-validator';
import { CreateBioAdditiveRemindDTO as CreateBioAdditiveToRemindDTO } from 'src/reminds/reminds.dto';

export class CreateBiologicalAdditiveDTO {
  author_id?: number;

  @IsString()
  @IsNotEmpty()
  name: string;

  @IsString()
  @IsNotEmpty()
  description: string;

  @IsOptional()
  @IsString()
  @IsEnum(BioType)
  bioType?: BioType;
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

  @IsOptional()
  @IsString()
  @IsEnum(BioType)
  bioType?: BioType;
  
}

export class CreateBiologicalAdditiveWithRemindsDTO {
  author_id?: number;

  @IsString()
  @IsNotEmpty()
  name: string;

  @IsString()
  @IsNotEmpty()
  description: string;

  @IsOptional()
  @IsString()
  @IsEnum(BioType)
  bioType?: BioType;

  reminds: CreateBioAdditiveToRemindDTO[];

  static toCreateInput(object : CreateBiologicalAdditiveWithRemindsDTO): Prisma.BiologicalAdditiveCreateInput {
    const createInput = {
      name: object.name,
      description: object.description,
      User: { connect: { id: object.author_id } },
    };
    return createInput;
  }

}

export class UpdateBiologicalAdditiveWithRemindsDTO {
  id: number;
  author_id?: number;

  @IsString()
  @IsNotEmpty()
  name: string;

  @IsString()
  @IsNotEmpty()
  description: string;


  @IsOptional()
  @IsString()
  @IsEnum(BioType)
  bioType?: BioType;

  reminds: CreateBioAdditiveToRemindDTO[];

  static toUpdateInput(object : UpdateBiologicalAdditiveWithRemindsDTO): Prisma.BiologicalAdditiveUpdateInput {
    const updateInput :  Prisma.BiologicalAdditiveUpdateInput  = {
      name: object.name,
      description: object.description,
      User: { connect: { id: object.author_id } },
    };
    return updateInput;
  }
}
