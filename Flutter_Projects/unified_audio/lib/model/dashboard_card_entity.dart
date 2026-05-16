class DashboardCardEntity {
  int id;
  String name;
  DashboardCardEntity(this.id, this.name);

  @override
  String toString() => "{$id, $name}";
}
