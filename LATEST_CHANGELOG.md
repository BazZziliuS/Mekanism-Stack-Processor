## [1.4.0] - 2026-11-09

### Added
- Batch processing for new machines:
  - MoreMachine Pressing Factory — uses factory tier multiplier
  - MoreMachine Painting and Pigment Extracting Factories — use chemical factory tier multiplier
  - Evolved Mekanism Fluid Alloyer and Fluid Combiner — use regular machine multiplier

### Changed
- Minimum versions raised: Mekanism 10.7.19, Mekanism:MoreMachine 1.4.2, Evolved Mekanism 1.3.0
- Built against NeoForge 21.1.256
- Mixins consolidated by machine group and no longer depend on recipe types
- Mod metadata now includes project links and credits, display name is "Mekanism Stack Processor"

### Fixed
- Compatibility with Mekanism:MoreMachine 1.4.2: moved factory base classes and the merged Oxidizing factory
- Compatibility with Evolved Mekanism 1.3.0: Melter now uses `MeltingRecipe`

### Removed
- Chemical Infusing and Solar Neutron Activating factory support, these factories no longer exist in Mekanism:MoreMachine
