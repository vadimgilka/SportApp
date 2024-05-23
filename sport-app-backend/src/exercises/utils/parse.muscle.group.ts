import { MuscleGroup } from '@prisma/client';

export function parseMuscleGroup(value: string): MuscleGroup {
  if (typeof value !== 'string') {
    throw new Error('value is not string');
  }

  if (!value || value.length < 2) {
    throw new Error('Invalid muscle group');
  }

  if (!(value in MuscleGroup)) {
    throw new Error('Invalid muscle group');
  }

  return MuscleGroup[value];
}
