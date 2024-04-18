import { MuscleGroup } from '@prisma/client';

export function parseMuscleGroup(value: string): MuscleGroup {
  if (typeof value !== 'string') {
    throw new Error('value is not string');
  }

  if (!value || value.length < 2) {
    throw new Error('Invalid muscle group');
  }

  const normalizedValue =
    value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();

  if (!(normalizedValue in MuscleGroup)) {
    throw new Error('Invalid muscle group');
  }

  return MuscleGroup[normalizedValue];
}
