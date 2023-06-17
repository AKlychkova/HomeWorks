using System.ComponentModel.DataAnnotations;

namespace colloquium.Models
{
    public class Workout
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string Instructor { get; set; }
        public int Duration { get; set; }
    }
}
