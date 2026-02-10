## [1.2.1] - 2025-02-10

### Fixed
- Fixed crash on startup with 300+ mods caused by premature BlockEntity class loading
  - Replaced `Class.forName()` with `ClassLoader.getResource()` in MoreMachineMixinPlugin to avoid cascading class hierarchy resolution
  - Resolves incompatibility with Collective, Data Anchor, EntityCulling, Sodium, and other mods that add BlockEntity mixins
- Added mixin config priority (1500) to both mixin JSON configs for additional safety
