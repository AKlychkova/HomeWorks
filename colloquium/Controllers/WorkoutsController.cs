using colloquium.Models;
using Microsoft.AspNetCore.Mvc;
using System.Xml.Linq;

namespace colloquium.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WorkoutsController : Controller
    {
        [HttpGet]
        public IActionResult GetAllWorkouts()
        {
            using (ApplicationContext db = new ApplicationContext())
            {
                return Ok(db.Workouts.ToList());
            }
        }

        [HttpGet("{id}")]
        public IActionResult GetWorkoutById(int id)
        {
            using (ApplicationContext db = new ApplicationContext())
            {
                Workout? workout = db.Workouts.SingleOrDefault(p => p.Id == id);
                if (workout == null)
                {
                    return NotFound(new { Message = $"Пользователь с Id = {id} не найден" });
                }
                return Ok(workout);
            }
        }

        [HttpPost]
        public IActionResult CreateWorkout([FromBody] CreateWorkoutRequest req)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            var workout = new Workout()
            {
                Name = req.Name,
                Description = req.Description,
                Instructor = req.Instructor,
                Duration = req.Duration
            };
            using (ApplicationContext db = new ApplicationContext())
            {
                db.Workouts.Add(workout);
                db.SaveChanges();
                return Ok(workout);
            }
        }

        [HttpPut("{id}")]
        public IActionResult UpdateWorkout(int id, [FromBody] UpdateWorkoutRequest req)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            using (ApplicationContext db = new ApplicationContext())
            {
                Workout? workout = db.Workouts.SingleOrDefault(p => p.Id == id);
                if (workout == null)
                {
                    return NotFound(new { Message = $"Пользователь с Id = {id} не найден" });
                }
                workout.Name = req.Name ?? workout.Name;
                workout.Description = req.Description ?? workout.Description;
                workout.Instructor = req.Instructor ?? workout.Instructor;
                workout.Duration = req.Duration ?? workout.Duration;

                db.Workouts.Update(workout);
                db.SaveChanges();

                return Ok(workout);
            }
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            using (ApplicationContext db = new ApplicationContext())
            {
                Workout? workout = db.Workouts.SingleOrDefault(p => p.Id == id);
                if (workout == null)
                {
                    return NotFound(new { Message = $"Пользователь с Id = {id} не найден" });
                }
                db.Workouts.Remove(workout);
                db.SaveChanges();
                return Ok();
            }
        }

    }
}
