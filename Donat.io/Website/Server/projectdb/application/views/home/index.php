        <form class="form" action="<?php echo URL; ?>home/formLogIn" method="POST">
					<h3 class="text-divider">Log In</h3>
					<div class="content">

						<div class="input-group form-group">
							<span class="input-group-addon">
								<i class="material-icons">face</i>
							</span>
							<input type="text" class="form-control" placeholder="Username..." name="username" required />
						</div>

						<div class="input-group form-group">
							<span class="input-group-addon">
								<i class="material-icons">lock_outline</i>
							</span>
							<input type="password" placeholder="Password..." class="form-control" required name="pass" />
						</div>

            <div class="text-center form-group">
              <button type="submit" class="btn btn-round btn-primary btn-lg" name="form_Log_In">Log In</button>
              <!-- <input type="submit" class="btn btn-round btn-primary btn-lg" value="Log In" name="form_Log_In" /> -->
            </div>
            <div class="footer text-center">
              <a href="<?php echo URL; ?>home/signup" class="btn btn-primary" role="button">New User? Sign Up</a>
            </div>
        </form>
        </div>
			</div>
		</div>
	</div>
</div>
