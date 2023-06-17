using colloquium.Models;
using Microsoft.EntityFrameworkCore;

namespace colloquium
{
    public class ApplicationContext : DbContext
    {
        public DbSet<Workout> Workouts => Set<Workout>();
        public ApplicationContext() => Database.EnsureCreated();

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Data Source=workouts.db");
        }
    }
}
